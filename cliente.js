const http = require('http');
var express = require('express')
var bodyParser = require('body-parser');
const { stringify } = require('querystring');
const multer = require('multer');
const fs = require('fs');
const path = require('path');
const xlsx = require('xlsx');
var app = express()
var urlParser = bodyParser.urlencoded({ extended: false })
const upload = multer({ dest: 'descargas/' });
var usuario = { user: "", password: "" }
let tipoUsuario = ''
let claveSelMod = 0

app.use(express.static('views'))

app.get('/', (req, res) => {
   res.render('login.ejs')
   tipoUsuario = ''
})

app.post('/validarDatos', urlParser, (req, res) => { // Validar datos del login
   usuario = {
      user: req.body.user,
      password: req.body.password
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
         tipoUsuario += d
      });

      response.on('end', () => {
         if (response.statusCode === 200) {
            if (tipoUsuario === "Profesor") {
               claveSelMod = 1
               res.render('selModulo.ejs', { clave: claveSelMod })
            } else if (tipoUsuario === "Asistente") {
               claveSelMod = 2
               res.render('selModulo.ejs', { clave: claveSelMod })
            }
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
         res.render("gestion.ejs", {
            clave: 1, tUsuario: tipoUsuario,
            arreglo: estudiantes
         });
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
            res.render("gestion.ejs", {
               clave: 2, tUsuario: tipoUsuario,
               arreglo: profesores
            });
         } else {
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
   const user = { user: usuario.user };
   postUser = JSON.stringify(user);

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/profesor/gestionarProfGuia',
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
            console.log(profesores);
            res.render("gestion.ejs", {
               clave: 3, tUsuario: tipoUsuario,
               arreglo: profesores
            });
         } else {
            console.log("ERROR: ResponseData - " + responseData);
         }
      });
   });

   request.on('error', (error) => {
      console.error(error);
   });

   request.write(postUser);

   request.end()
   //res.render("gestion.ejs", {clave: 3, arreglo: []})
})

app.post('/salirGestion', urlParser, upload.any(), (req, res) => {
   res.render("selModulo.ejs", { clave: claveSelMod })
})

app.post("/agregarProf", urlParser, upload.any(), (req, res) => {
   res.render("datosProfes.ejs", { profe: { id: "", nombre: "", correo: "", pass: "", tel: "", cel: "" } })
})

app.post("/modProf", urlParser, upload.any(), (req, res) => {
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
         } else {
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

   if (entryId != "") {
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
                           res.render("gestion.ejs", { clave: 2, tUsuario: tipoUsuario, arreglo: profesores });
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
               } else {
                  console.log("ERROR: ResponseData - " + responseData);
               }

            });
         });

         request1.on('error', (error1) => {
            console.error(error1);
         });

         request1.write(profeJson);
         request1.end();
      } else {
         console.log("Revisa que ningun campo este vacio.");
      }
   } else {
      if (entryName && entryCE && entryPass && entryTel && entryCel) {
         const profe = {
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
            path: '/profesor/agregarProf',
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
                           res.render("gestion.ejs", { clave: 2, tUsuario: tipoUsuario, arreglo: profesores });
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
               } else {
                  console.log("ERROR: ResponseData - " + responseData);
               }

            });
         });

         request1.on('error', (error1) => {
            console.error(error1);
         });

         request1.write(profeJson);
         request1.end();
      } else {
         console.log("Revisa que ningun campo este vacio excepto ID.");
      }
   }
});

app.post("/bajaProf", urlParser, upload.any(), (req, res) => {
   if (req.body.btnBajaProfGuia == "1") {
      const codigo = JSON.stringify({
         codigo: req.body.elementosTabla,
         user: usuario.user
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
                        res.render("gestion.ejs", {
                           clave: 2, tUsuario: tipoUsuario,
                           arreglo: innerProfesores
                        });
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

app.post("/defGuia", urlParser, upload.any(), (req, res) => {
   if (req.body.btnDefProfGuia == "1") {
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
                        res.render("gestion.ejs", {
                           clave: 2, tUsuario: tipoUsuario,
                           arreglo: innerProfesores
                        });
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
app.post("/modEst", urlParser, upload.any(), (req, res) => {

   console.log(req.body.elementosTabla)

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
         } else {
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
//res.render("gestion.ejs", {clave: 1, arreglo: []})
app.post("/datosEstRes", urlParser, (req, res) => {

   const entryId = req.body.entryId;
   const entryName = req.body.entryName;
   const entryApellido1 = req.body.entryApellido1;
   const entryApellido2 = req.body.entryApellido2;
   const entryCE = req.body.entryCE;
   const entryPass = req.body.entryPass;
   //const entryTel = req.body.entryTel;
   const entryCel = req.body.entryCel;

   if (entryId && entryName && entryApellido1 && entryApellido2 && entryCE && entryCel && entryPass) {
      const est = {
         id: entryId,
         nombre: entryName,
         apellido1: entryApellido1,
         apellido2: entryApellido2,
         correo: entryCE,
         pass: entryPass,
         tel: entryCel,
         user: usuario.user
      };

      const estJson = JSON.stringify(est);

      const options1 = {
         hostname: 'localhost',
         port: 8080,
         path: '/estudiante/datosEstRes',
         method: 'POST',
         headers: {
            'Content-Type': 'application/json',
            'Content-Length': estJson.length
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
                  path: '/estudiante/gestionarEst',
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
                        const estudiantes = JSON.parse(responseData2);
                        res.render("gestion.ejs", {
                           clave: 1, tUsuario: tipoUsuario,
                           arreglo: estudiantes
                        });
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
            } else {
               console.log("ERROR: ResponseData - " + responseData);
            }

         });
      });

      request1.on('error', (error1) => {
         console.error(error1);
      });

      request1.write(estJson);
      request1.end();
   } else {
      console.log("Revisa que ningun campo este vacio.");
   }
})

app.post("/agrActividad", urlParser, (req, res) => {
   res.render("crearActividad.ejs")
})

app.post("/gestionPlanTrabajo", urlParser, (req, res) => {
   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/plantrabajo/obtenerActividades',
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      }
   };

   const request = http.request(options, (response) => {
      let responseData = '';

      response.on('data', (chunk) => {
         responseData += chunk;
      });

      response.on('end', () => {
         if (response.statusCode === 200) {
            const actividades = JSON.parse(responseData);
            actividades.forEach((actividad) => {
               const fechaHoraOriginal = new Date(actividad.FechaHora);
               const fechaHoraFormateada = fechaHoraOriginal.toLocaleString();
               actividad.FechaHora = fechaHoraFormateada;
            });
            res.render("gestionPlanTrabajo.ejs", { arreglo: actividades })
         } else {
            console.log("ERROR: ResponseData - " + responseData);
         }
      });
   });

   request.on('error', (error) => {
      console.error(error);
   });

   request.end()
})

app.post("/datosActRes", urlParser, (req, res) => {
   const entrySemana = req.body.entrySemana;
   const entryTipo = req.body.entryTipo;
   const entryNombre = req.body.entryNombre;
   const entryFecha = req.body.entryFecha;
   const entryHora = req.body.entryHora;
   const entryFechaP = req.body.entryFechaP;
   const entryRes = req.body.entryRes;
   const entryModalidad = req.body.entryModalidad;
   const entryEnlace = req.body.entryEnlace;
   const entryEstado = req.body.entryEstado;

   // Validar formatos
   const fechaRegex = /^\d{4}-\d{2}-\d{2}$/;
   const horaRegex = /^\d{2}:\d{2}$/;

   if (!fechaRegex.test(entryFecha)) {
      console.log("El formato de Fecha debe ser yyyy-MM-dd");
      return;
   }

   if (!horaRegex.test(entryHora)) {
      console.log("El formato de Hora debe ser HH:mm");
      return;
   }

   if (!fechaRegex.test(entryFechaP)) {
      console.log("Fecha de publicación debe ser yyyy-MM-dd");
      return;
   }

   if (entrySemana && entryTipo && entryFecha && entryNombre && entryHora && entryFechaP && entryRes && entryModalidad && entryEstado) {
      const actividad = {
         idPlanDeTrabajo: 1,
         tipoActividad: entryTipo,
         semana: entrySemana,
         nombre: entryNombre,
         fechaPublicacion: entryFechaP,
         responsableProfesor: entryRes,
         modalidad: entryModalidad,
         enlace: entryEnlace,
         estado: entryEstado,
         fecha: entryFecha,
         hora: entryHora,
         user: usuario.user
      };

      const actividadJson = JSON.stringify(actividad);

      const options1 = {
         hostname: 'localhost',
         port: 8080,
         path: '/plantrabajo/agregarAct',
         method: 'POST',
         headers: {
            'Content-Type': 'application/json',
            'Content-Length': actividadJson.length
         }
      };

      const request1 = http.request(options1, (response1) => {
         let responseData = '';

         response1.on('data', (chunk) => {
            responseData += chunk;
         });

         response1.on('end', () => {
            if (response1.statusCode === 200) {
               const options2 = {
                  hostname: 'localhost',
                  port: 8080,
                  path: '/plantrabajo/obtenerActividades',
                  method: 'POST',
                  headers: {
                     'Content-Type': 'application/json',
                  }
               };

               const request2 = http.request(options2, (response2) => {
                  let responseData2 = '';

                  response2.on('data', (chunk2) => {
                     responseData2 += chunk2;
                  });

                  response2.on('end', () => {
                     if (response2.statusCode === 200) {
                        const actividades = JSON.parse(responseData2);
                        actividades.forEach((actividad) => {
                           const fechaHoraOriginal = new Date(actividad.FechaHora);
                           const fechaHoraFormateada = fechaHoraOriginal.toLocaleString();
                           actividad.FechaHora = fechaHoraFormateada;
                        });
                        res.render("gestionPlanTrabajo.ejs", { arreglo: actividades })
                     } else {
                        console.log("ERROR: ResponseData - " + responseData2);
                     }
                  });
               });

               request2.on('error', (error2) => {
                  console.error(error2);
               });

               request2.end();
            } else {
               console.log("ERROR: ResponseData - " + responseData);
            }
         });
      });

      request1.on('error', (error1) => {
         console.error(error1);
      });

      request1.write(actividadJson);
      request1.end();
   } else {
      console.log("Revisa que ningun campo este vacio excepto enlace si aun no tienes uno.");
   }
})

app.post("/marcarActRealizada", urlParser, (req, res) => {
   if ("1" == "1") {
      const codigo = JSON.stringify({
         codigo: req.body.elementosTabla,
         estado: 3
      });

      const options = {
         hostname: 'localhost',
         port: 8080,
         path: '/plantrabajo/marcarActividad',
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
               const options2 = {
                  hostname: 'localhost',
                  port: 8080,
                  path: '/plantrabajo/obtenerActividades',
                  method: 'POST',
                  headers: {
                     'Content-Type': 'application/json',
                  }
               };

               const request2 = http.request(options2, (response2) => {
                  let responseData2 = '';

                  response2.on('data', (chunk2) => {
                     responseData2 += chunk2;
                  });

                  response2.on('end', () => {
                     if (response2.statusCode === 200) {
                        const actividades = JSON.parse(responseData2);
                        actividades.forEach((actividad) => {
                           const fechaHoraOriginal = new Date(actividad.FechaHora);
                           const fechaHoraFormateada = fechaHoraOriginal.toLocaleString();
                           actividad.FechaHora = fechaHoraFormateada;
                        });
                        res.render("gestionPlanTrabajo.ejs", { arreglo: actividades })
                     } else {
                        console.log("ERROR: ResponseData - " + responseData2);
                     }
                  });
               });

               request2.on('error', (error2) => {
                  console.error(error2);
               });

               request2.end();
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

app.post("/marcarActCancelada", urlParser, (req, res) => {
   if ("1" == "1") {
      const codigo = JSON.stringify({
         codigo: req.body.elementosTabla,
         estado: 4
      });

      const options = {
         hostname: 'localhost',
         port: 8080,
         path: '/plantrabajo/marcarActividad',
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
               const options2 = {
                  hostname: 'localhost',
                  port: 8080,
                  path: '/plantrabajo/obtenerActividades',
                  method: 'POST',
                  headers: {
                     'Content-Type': 'application/json',
                  }
               };

               const request2 = http.request(options2, (response2) => {
                  let responseData2 = '';

                  response2.on('data', (chunk2) => {
                     responseData2 += chunk2;
                  });

                  response2.on('end', () => {
                     if (response2.statusCode === 200) {
                        const actividades = JSON.parse(responseData2);
                        actividades.forEach((actividad) => {
                           const fechaHoraOriginal = new Date(actividad.FechaHora);
                           const fechaHoraFormateada = fechaHoraOriginal.toLocaleString();
                           actividad.FechaHora = fechaHoraFormateada;
                        });
                        res.render("gestionPlanTrabajo.ejs", { arreglo: actividades })
                     } else {
                        console.log("ERROR: ResponseData - " + responseData2);
                     }
                  });
               });

               request2.on('error', (error2) => {
                  console.error(error2);
               });

               request2.end();
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

app.post("/marcarActPublicada", urlParser, (req, res) => {
   if ("1" == "1") {
      const codigo = JSON.stringify({
         codigo: req.body.elementosTabla,
         estado: 2
      });

      const options = {
         hostname: 'localhost',
         port: 8080,
         path: '/plantrabajo/marcarActividad',
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
               const options2 = {
                  hostname: 'localhost',
                  port: 8080,
                  path: '/plantrabajo/obtenerActividades',
                  method: 'POST',
                  headers: {
                     'Content-Type': 'application/json',
                  }
               };

               const request2 = http.request(options2, (response2) => {
                  let responseData2 = '';

                  response2.on('data', (chunk2) => {
                     responseData2 += chunk2;
                  });

                  response2.on('end', () => {
                     if (response2.statusCode === 200) {
                        const actividades = JSON.parse(responseData2);
                        actividades.forEach((actividad) => {
                           const fechaHoraOriginal = new Date(actividad.FechaHora);
                           const fechaHoraFormateada = fechaHoraOriginal.toLocaleString();
                           actividad.FechaHora = fechaHoraFormateada;
                        });
                        res.render("gestionPlanTrabajo.ejs", { arreglo: actividades })
                     } else {
                        console.log("ERROR: ResponseData - " + responseData2);
                     }
                  });
               });

               request2.on('error', (error2) => {
                  console.error(error2);
               });

               request2.end();
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

var codigoActividadComentario = null;

app.post("/comentario", urlParser, (req, res) => {
   if ("1" == "1") {
      codigoActividadComentario = JSON.stringify({
         codigo: req.body.elementosTabla,
      });

      const options = {
         hostname: 'localhost',
         port: 8080,
         path: '/plantrabajo/obtenerComentarios',
         method: 'POST',
         headers: {
            'Content-Type': 'application/json',
            'Content-Length': Buffer.byteLength(codigoActividadComentario),
         }
      };

      const request = http.request(options, (response) => {
         let responseData = '';

         response.on('data', (chunk) => {
            responseData += chunk;
         });

         response.on('end', () => {
            if (response.statusCode === 200) {
               const comentarios = JSON.parse(responseData);
               res.render("comentarios.ejs", { comentarios: comentarios })
            }
         });
      });

      request.on('error', (error) => {
         console.error(error);
      });

      request.write(codigoActividadComentario);
      request.end();
   }
})

app.post("/comentarios", urlParser, (req, res) => {
   const codigo = JSON.stringify({
      user: usuario.user,
      reply: req.body.rdBtnComentario,
      mensaje: req.body.mensaje
   });

   console.log(codigo.toString());

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/plantrabajo/agregarComentario',
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
            const options1 = {
               hostname: 'localhost',
               port: 8080,
               path: '/plantrabajo/obtenerComentarios',
               method: 'POST',
               headers: {
                  'Content-Type': 'application/json',
                  'Content-Length': Buffer.byteLength(codigoActividadComentario),
               }
            };
      
            const request1 = http.request(options1, (response1) => {
               let responseData1 = '';
      
               response1.on('data', (chunk) => {
                  responseData1 += chunk;
               });
      
               response1.on('end', () => {
                  if (response1.statusCode === 200) {
                     const comentarios = JSON.parse(responseData1);
                     res.render("comentarios.ejs", { comentarios: comentarios })
                  }
               });
            });
      
            request1.on('error', (error) => {
               console.error(error);
            });
      
            request1.write(codigoActividadComentario);
            request1.end();
         }else{
            console.log("ERROR: ResponseData - " + responseData);
         }
      });
   });

   request.on('error', (error) => {
      console.error(error);
   });

   request.write(codigo);
   request.end();
})

app.post("/salirGestionPT", urlParser, (req, res) => {
   res.render("selModulo.ejs")
})

app.post("/salirLogin", urlParser, (req, res) => {
   res.render("login.ejs")
   tipoUsuario = ''
})

app.post("/generarExcel", urlParser, upload.any(), (req, res) => {

   const user = { user: usuario.user };
   postUser = JSON.stringify(user);

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/estudiante/generarExcel',
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
         'Content-Length': Buffer.byteLength(postUser),
      }
   };

   const request = http.request(options, response => {
      console.log(`statusCode: ${response.statusCode}`);

      let responseData = '';

      response.on('data', (chunk) => {
         responseData += chunk;
      });

      response.on('data', d => {
         process.stdout.write(d)
      });

      response.on('end', () => {
         if (response.statusCode == 200) {
            const estudiantes = JSON.parse(responseData)
            console.log(estudiantes)
            res.render("gestion.ejs", {
               clave: 1, tUsuario: tipoUsuario,
               arreglo: estudiantes
            })
         }
      });
   });

   request.on('error', error => {
      console.error(error);
   });

   request.write(postUser);
   request.end();
})

app.post("/cargarExcel", urlParser, upload.single("btnImpExcel"), (req, res) => {

   const archivo = req.file;
   const original = archivo.path;
   console.log(original)
   const nuevoNombre = 'estudiantes.xlsx';
   const nuevaRuta = path.join(path.dirname(original), nuevoNombre);

   const workbook = xlsx.readFile(original);
   const primeraHoja = workbook.Sheets[workbook.SheetNames[0]];
   const datos = xlsx.utils.sheet_to_json(primeraHoja, { header: 1 });
   console.log(datos)
   xlsx.writeFile(workbook, nuevaRuta);

   const data = {
      user: usuario.user,
      path: original
   };
   const postData = JSON.stringify(data);

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/estudiante/cargarExcel',
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
         'Content-Length': Buffer.byteLength(postData),
      }
   };

   const request = http.request(options, response => {
      console.log(`statusCode: ${response.statusCode}`);

      let responseData = '';

      response.on('data', (chunk) => {
         responseData += chunk;
      });

      response.on('end', () => {
         if (response.statusCode == 200) {
            const estudiantes = JSON.parse(responseData)
            console.log(estudiantes)
            res.render("gestion.ejs", {
               clave: 1, tUsuario: tipoUsuario,
               arreglo: estudiantes
            })
         }
      });
   });

   request.on('error', error => {
      console.error(error);
   });

   request.write(postData)
   request.end();
})

var server = app.listen(3000, function () {
   var host = server.address().address
   var port = server.address().port

   console.log("Example app listening at http://%s:%s", host, port)
})

app.post("/defCoord", urlParser, upload.any(), (req, res) => {
   console.log("AQUI1")
   const codigo = JSON.stringify({
      user: usuario.user,
      codigo: req.body.elementosTabla
   });
   console.log(codigo)

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/profesor/defCoord',
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
               path: '/profesor/gestionarProfGuia',
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
                     res.render("gestion.ejs", {
                        clave: 3, tUsuario: tipoUsuario,
                        arreglo: innerProfesores
                     });
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
})