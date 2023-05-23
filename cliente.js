const http = require('http');
var express = require('express')
var bodyParser = require('body-parser')
var app = express()
var urlParser = bodyParser.urlencoded({extended: false})
var usuario = {user: "", password: ""}

app.use(express.static('views'))


/////////////////////////////////////////////////////
// EJEMPLOS DE ESTUDIANTES
/////////////////////////////////////////////////////

var profes = [
   {
      id: "CA-1",
      nombre: "Jorge Vargas",
      correo: "jvargas@itcr",
      tel: "22650000",
      cel: "87878787",
      guia: false
   },
   {
      id: "CA-2",
      nombre: "Jorge Vargas",
      correo: "jvargas@itcr",
      tel: "22650000",
      cel: "87878787",
      guia: false
   },
   {
      id: "CA-3",
      nombre: "Jorge Vargas",
      correo: "jvargas@itcr",
      tel: "22650000",
      cel: "87878787",
      guia: false
   },
   {
      id: "CA-4",
      nombre: "Jorge Vargas",
      correo: "jvargas@itcr",
      tel: "22650000",
      cel: "87878787",
      guia: false
   },
   {
      id: "CA-5",
      nombre: "Jorge Vargas",
      correo: "jvargas@itcr",
      tel: "22650000",
      cel: "87878787",
      guia: false
   },
   {
      id: "CA-6",
      nombre: "Jorge Vargas",
      correo: "jvargas@itcr",
      tel: "22650000",
      cel: "87878787",
      guia: false
   },
   {
      id: "CA-7",
      nombre: "Jorge Vargas",
      correo: "jvargas@itcr",
      tel: "22650000",
      cel: "87878787",
      guia: false
   },
   {
      id: "CA-8",
      nombre: "Jorge Vargas",
      correo: "jvargas@itcr",
      tel: "22650000",
      cel: "87878787",
      guia: false
   },
   {
      id: "CA-9",
      nombre: "Jorge Vargas",
      correo: "jvargas@itcr",
      tel: "22650000",
      cel: "87878787",
      guia: false
   }
]




app.get('/', (req, res) => {
   res.render('login.ejs')
})

app.post('/validarDatos', urlParser, (req, res) => { // Validar datos del login
   usuario = {
      user : req.body.user,
      password : req.body.password
   }
   const data = JSON.stringify(usuario);

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/validarDatos',
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
         'Content-Length': data.length
      }
   };

   const request = http.request(options, response => {
   console.log(`statusCode: ${response.statusCode}`);
    
      response.on('data', d => {
        process.stdout.write(d);
      });
    
      response.on('end', () => {
        if (response.statusCode === 200) {
          res.render('selModulo.ejs');
        }
      });
   });
    
   request.on('error', error => {
      console.error(error);
   });

   request.write(data);
   request.end();
})

app.post('/gestionarEst', urlParser, (req, res) => {
   const user = { user: usuario.user };
   postUser = JSON.stringify(user);

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/estudiante/gestionarEst',
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
         'Content-Length': Buffer.byteLength(postUser),
      }
   };
   
   const request = http.request(options, (response) => {
      let responseData = '';
   
      response.on('data', (chunk) => {
         responseData += chunk;
         });
      
      response.on('end', () => {
         const estudiantes = JSON.parse(responseData);
         console.log(estudiantes);
         res.render("gestion.ejs", {clave: 1, arreglo : estudiantes});
         });
      });
      
   request.on('error', (error) => {
      console.error(error);
   });

   request.write(postUser);
   
   request.end()
});


app.post('/gestionarProf', urlParser, (req, res) => {
   const user = { user: usuario.user };
   postUser = JSON.stringify(user);

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/profesor/gestionarProf',
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
         'Content-Length': Buffer.byteLength(postUser),
      }
   };
   
   const request = http.request(options, (response) => {
      let responseData = '';
   
      response.on('data', (chunk) => {
         responseData += chunk;
         });
      
      response.on('end', () => {
         if (response.statusCode === 200) {
            const profesores = JSON.parse(responseData);
            res.render("gestion.ejs", {clave: 2, arreglo : profesores});
         }else{
            console.log("ERROR: ResponseData - " + responseData);   
            }
      });
   });
      
   request.on('error', (error) => {
      console.error(error);
   });

   request.write(postUser);
   
   request.end()
   });

app.post('/gestionarGuias', urlParser, (req, res) => {
   let guias = []
   for (profe of profes) {
      if (profe["guia"]) {
         guias.push(profe)
      }
   }
   res.render("gestion.ejs", {clave: 3, arreglo: guias})
})

app.post('/salirGestion', urlParser, (req, res) => {
   res.render("selModulo.ejs")
})

app.post("/agregarProf", urlParser, (req, res) => {
   res.render("datosProfes.ejs", {profe: {id: "", nombre: "", correo: "", pass: "", tel: "", cel: ""}})
})

app.post("/modProf", urlParser, (req, res) => {
   const codigo = JSON.stringify({
      codigo: req.body.elementosTabla
    });

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/profesor/modProf',
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      }
   };

   options.headers['Content-Length'] = codigo.length;

   const request = http.request(options, (response) => {
      let responseBody = '';

      response.on('data', (chunk) => {
         responseBody += chunk;
      });

      response.on('end', () => {
         if (response.statusCode === 200) {
            const profesor = JSON.parse(responseBody);
            res.render("datosProfes.ejs", { profe: profesor });
         }else{
            console.log("ERROR: " + responseBody);
         }

      });
   });

   request.on('error', (error) => {
      console.error('Error al realizar la solicitud:', error);
      res.status(500).send('Error interno del servidor');
   });

   request.write(codigo);
   request.end();
});

app.post("/datosProfesRes", urlParser, (req, res) => {
   const entryId = req.body.entryId;
   const entryName = req.body.entryName;
   const entryCE = req.body.entryCE;
   const entryPass = req.body.entryPass;
   const entryTel = req.body.entryTel;
   const entryCel = req.body.entryCel;

   if (entryId && entryName && entryCE && entryPass && entryTel && entryCel) {
      const profe = {
         id: entryId,
         nombre: entryName,
         correo: entryCE,
         pass: entryPass,
         tel: entryTel,
         cel: entryCel
      };

      const profeJson = JSON.stringify(profe);

      const options1 = {
         hostname: 'localhost',
         port: 8080,
         path: '/profesor/datosProfesRes',
         method: 'POST',
         headers: {
            'Content-Type': 'application/json',
            'Content-Length': profeJson.length
         }
      };

      const request1 = http.request(options1, (response1) => {
         let responseData = '';

         response1.on('data', (chunk) => {
            responseData += chunk;
         });

         response1.on('end', () => {
            if (response1.statusCode === 200) {
               const user = { user: usuario.user };
               const postUser = JSON.stringify(user);

               const options2 = {
                  hostname: 'localhost',
                  port: 8080,
                  path: '/profesor/gestionarProf',
                  method: 'POST',
                  headers: {
                     'Content-Type': 'application/json',
                     'Content-Length': Buffer.byteLength(postUser),
                  }
               };

               const request2 = http.request(options2, (response2) => {
                  let responseData2 = '';

                  response2.on('data', (chunk2) => {
                     responseData2 += chunk2;
                  });

                  response2.on('end', () => {
                     if (response2.statusCode === 200) {
                        const profesores = JSON.parse(responseData2);
                        res.render("gestion.ejs", { clave: 2, arreglo: profesores });
                     } else {
                        console.log("ERROR: ResponseData - " + responseData2);
                     }
                  });
               });

               request2.on('error', (error2) => {
                  console.error(error2);
               });

               request2.write(postUser);
               request2.end();
            }else{
               console.log("ERROR: ResponseData - " + responseData);   
               }
            
         });
      });

      request1.on('error', (error1) => {
         console.error(error1);
      });

      request1.write(profeJson);
      request1.end();
   }else{
      console.log("Revisa que ningun campo este vacio.");
   } 
});

app.post("/datosProfesRes2", urlParser, (req, res) => {
   const entryId = req.body.entryId;
   const entryName = req.body.entryName;
   const entryCE = req.body.entryCE;
   const entryPass = req.body.entryPass;
   const entryTel = req.body.entryTel;
   const entryCel = req.body.entryCel;

   if (entryId && entryName && entryCE && entryPass && entryTel && entryCel) {
      const profe = {
         id: entryId,
         nombre: entryName,
         correo: entryCE,
         pass: entryPass,
         tel: entryTel,
         cel: entryCel,
         user: usuario.user
      };

      const profeJson = JSON.stringify(profe);

      const options1 = {
         hostname: 'localhost',
         port: 8080,
         path: '/profesor/agregarProfesor',
         method: 'POST',
         headers: {
            'Content-Type': 'application/json',
            'Content-Length': profeJson.length
         }
      };

      const request1 = http.request(options1, (response1) => {
         let responseData = '';

         response1.on('data', (chunk) => {
            responseData += chunk;
         });

         response1.on('end', () => {
            if (response1.statusCode === 200) {
               const user = { user: usuario.user };
               const postUser = JSON.stringify(user);

               const options2 = {
                  hostname: 'localhost',
                  port: 8080,
                  path: '/profesor/gestionarProf',
                  method: 'POST',
                  headers: {
                     'Content-Type': 'application/json',
                     'Content-Length': Buffer.byteLength(postUser),
                  }
               };

               const request2 = http.request(options2, (response2) => {
                  let responseData2 = '';

                  response2.on('data', (chunk2) => {
                     responseData2 += chunk2;
                  });

                  response2.on('end', () => {
                     if (response2.statusCode === 200) {
                        const profesores = JSON.parse(responseData2);
                        res.render("gestion.ejs", { clave: 2, arreglo: profesores });
                     } else {
                        console.log("ERROR: ResponseData - " + responseData2);
                     }
                  });
               });

               request2.on('error', (error2) => {
                  console.error(error2);
               });

               request2.write(postUser);
               request2.end();
            }else{
               console.log("ERROR: ResponseData - " + responseData);   
               }
            
         });
      });

      request1.on('error', (error1) => {
         console.error(error1);
      });

      request1.write(profeJson);
      request1.end();
   }else{
      console.log("Revisa que ningun campo este vacio.");
   } 
});

app.post("/bajaProf", urlParser, (req, res) => {
   if (req.body.btnBajaProfGuia == "1") {
      const codigo = JSON.stringify({
         codigo: req.body.elementosTabla
      });
   
      const options = {
         hostname: 'localhost',
         port: 8080,
         path: '/profesor/bajaProf',
         method: 'POST',
         headers: {
            'Content-Type': 'application/json',
            'Content-Length': Buffer.byteLength(codigo),
         }
      };
   
      const request = http.request(options, (response) => {
         let responseData = '';

         response.on('data', (chunk) => {
            responseData += chunk;
         });

         response.on('end', () => {
            if (response.statusCode === 200) {

               // Realizar el segundo request HTTP anidado
               const user = { user: usuario.user };
               postUser = JSON.stringify(user);

               const innerOptions = {
                  hostname: 'localhost',
                  port: 8080,
                  path: '/profesor/gestionarProf',
                  method: 'POST',
                  headers: {
                     'Content-Type': 'application/json',
                     'Content-Length': Buffer.byteLength(postUser),
                  }
               };

               const innerRequest = http.request(innerOptions, (innerResponse) => {
                  let innerResponseData = '';

                  innerResponse.on('data', (innerChunk) => {
                     innerResponseData += innerChunk;
                  });

                  innerResponse.on('end', () => {
                     if (innerResponse.statusCode === 200) {
                        const innerProfesores = JSON.parse(innerResponseData);
                        res.render("gestion.ejs", { clave: 2, arreglo: innerProfesores });
                     } else {
                        console.log("ERROR: Inner ResponseData - " + innerResponseData);
                     }
                  });
               });

               innerRequest.on('error', (innerError) => {
                  console.error(innerError);
               });

               innerRequest.write(postUser);
               innerRequest.end();
            } else {
               console.log("ERROR: ResponseData - " + responseData);
            }
         });
      });

      request.on('error', (error) => {
      console.error(error);
      });

      request.write(codigo);
      request.end();
   }
})

app.post("/defGuia", urlParser, (req, res) => {
   if (req.body.btnDefProfGuia== "1") {
      const codigo = JSON.stringify({
         codigo: req.body.elementosTabla
      });
   
      const options = {
         hostname: 'localhost',
         port: 8080,
         path: '/profesor/defGuia',
         method: 'POST',
         headers: {
            'Content-Type': 'application/json',
            'Content-Length': Buffer.byteLength(codigo),
         }
      };
   
      const request = http.request(options, (response) => {
         let responseData = '';

         response.on('data', (chunk) => {
            responseData += chunk;
         });

         response.on('end', () => {
            if (response.statusCode === 200) {

               const user = { user: usuario.user };
               postUser = JSON.stringify(user);

               const innerOptions = {
                  hostname: 'localhost',
                  port: 8080,
                  path: '/profesor/gestionarProf',
                  method: 'POST',
                  headers: {
                     'Content-Type': 'application/json',
                     'Content-Length': Buffer.byteLength(postUser),
                  }
               };

               const innerRequest = http.request(innerOptions, (innerResponse) => {
                  let innerResponseData = '';

                  innerResponse.on('data', (innerChunk) => {
                     innerResponseData += innerChunk;
                  });

                  innerResponse.on('end', () => {
                     if (innerResponse.statusCode === 200) {
                        const innerProfesores = JSON.parse(innerResponseData);
                        res.render("gestion.ejs", { clave: 2, arreglo: innerProfesores });
                     } else {
                        console.log("ERROR: Inner ResponseData - " + innerResponseData);
                     }
                  });
               });

               innerRequest.on('error', (innerError) => {
                  console.error(innerError);
               });

               innerRequest.write(postUser);
               innerRequest.end();
            } else {
               console.log("ERROR: ResponseData - " + responseData);
            }
         });
      });

      request.on('error', (error) => {
         console.error(error);
      });

      request.write(codigo);
      request.end();
   }
})
//res.render("modEst.ejs", est)
app.post("/modEst", urlParser, (req, res) => {
   const codigo = JSON.stringify({
      codigo: req.body.elementosTabla
    });

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/estudiante/modEst',
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      }
   };

   options.headers['Content-Length'] = codigo.length;

   const request = http.request(options, (response) => {
      let responseBody = '';

      response.on('data', (chunk) => {
         responseBody += chunk;
      });

      response.on('end', () => {
         if (response.statusCode === 200) {
            const estudiante = JSON.parse(responseBody);
            console.log(estudiante)
            res.render("modEst.ejs", { est: estudiante });
         }else{
            console.log("ERROR: " + responseBody);
         }

      });
   });

   request.on('error', (error) => {
      console.error('Error al realizar la solicitud:', error);
      res.status(500).send('Error interno del servidor');
   });

   request.write(codigo);
   request.end();
});

app.post("/datosEstRes", urlParser, (req, res) => {
   estudiante = {id: req.body.entryId,
      nombre: req.body.entryName,
      apellido1: req.body.entryApellido1,
      apellido2: req.body.entryApellido2,
      correo: req.body.entryCE,
      constrasena: req.body.entryPass,
      cel: req.body.entryCel
   }
   console.log(estudiante)
   res.render("gestion.ejs", {})
})

app.post("/agrActividad", urlParser, (req, res) => {
   res.render("crearActividad.ejs")
})


var server = app.listen(3000, function () {
   var host = server.address().address
   var port = server.address().port
   
   console.log("Example app listening at http://%s:%s", host, port)
})