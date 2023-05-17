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

var estudiantes = [
   {
      id: "2020084831",
      nombre: "Gustavo Pérez Badilla",
      correo: "gperezb2002@estudiantec.cr",
      tel: "22658776",
      cel: "86435450"
   },
   {
      id: "2020084832",
      nombre: "Gustavo Pérez Badilla",
      correo: "gperezb2002@estudiantec.cr",
      tel: "22658776",
      cel: "86435450"
   },
   {
      id: "2020084833",
      nombre: "Gustavo Pérez Badilla",
      correo: "gperezb2002@estudiantec.cr",
      tel: "22658776",
      cel: "86435450"
   },
   {
      id: "2020084834",
      nombre: "Gustavo Pérez Badilla",
      correo: "gperezb2002@estudiantec.cr",
      tel: "22658776",
      cel: "86435450"
   },
   {
      id: "2020084835",
      nombre: "Gustavo Pérez Badilla",
      correo: "gperezb2002@estudiantec.cr",
      tel: "22658776",
      cel: "86435450"
   },
   {
      id: "2020084836",
      nombre: "Gustavo Pérez Badilla",
      correo: "gperezb2002@estudiantec.cr",
      tel: "22658776",
      cel: "86435450"
   },
   {
      id: "2020084837",
      nombre: "Gustavo Pérez Badilla",
      correo: "gperezb2002@estudiantec.cr",
      tel: "22658776",
      cel: "86435450"
   },
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

   res.render('selModulo.ejs');
   //res.render('gestionPlanTrabajo.ejs', {arreglo: []})
})

app.post('/gestionarEst', urlParser, (req, res) => {
   const options = {
   hostname: 'localhost',
   port: 8080,
   path: '/gestionarEst',
   method: 'POST',
   headers: {
      'Content-Type': 'application/json',
      'Content-Length': data.length
   }
   };

   const request = http.request(options, (res) => {
   let responseData = '';

      request.on('data', (chunk) => {
         responseData += chunk;
         });

      request.on('end', () => {
         const estudiantes = JSON.parse(responseBody);
         res.render("gestion.ejs", {clave: 1,arreglo : estudiantes });
         });
      });

   request.on('error', (error) => {
      console.error(error);
   });

   request.write(data);
   request.end()
});



app.post('/gestionarProf', urlParser, (req, res) => {
   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/gestionarProf',
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
         'Content-Length': data.length
      }
      };
   
   const request = http.request(options, (res) => {
      let responseData = '';
   
      request.on('data', (chunk) => {
         responseData += chunk;
         });
      
      request.on('end', () => {
         const profesores = JSON.parse(responseBody);
         res.render("gestion.ejs", {clave: 1,arreglo : profesores });
         });
      });
      
   request.on('error', (error) => {
      console.error(error);
   });
   
   request.write(data);
   request.end()
})

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
   const profesor = {
      id: "",
      nombre: "",
      correo: "",
      pass: "",
      tel: "",
      cel: ""
   };

   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/modProf',
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      }
   };

   const data = JSON.stringify(profesor);
   options.headers['Content-Length'] = data.length;

   const request = http.request(options, (response) => {
      let responseBody = '';

      response.on('data', (chunk) => {
         responseBody += chunk;
      });

      response.on('end', () => {
         const profesor = JSON.parse(responseBody);
         res.render("datosProfes.ejs", { profe: profesor });
      });
   });

   request.on('error', (error) => {
      console.error('Error al realizar la solicitud:', error);
      res.status(500).send('Error interno del servidor');
   });

   request.write(data);
   request.end();
});

app.post("/datosProfesRes", urlParser, (req, res) => {
   profe = {id: req.body.entryId,
      nombre: req.body.entryName,
      correo: req.body.entryCE,
      pass: req.body.entryPass,
      tel: req.body.entryTel,
      cel: req.body.entryCel
   }
   //Obtiene los datos del profesor
   //
   console.log(profe)
   res.render("gestion.ejs", {clave: 2, arreglo: []})
})

app.post("/bajaProf", urlParser, (req, res) => {
   if (req.body.btnBajaProfGuia == "1") {
      //Codigo que elimina al profe guia
      console.log(req.body.elementosTabla)
   }
   res.render("gestion.ejs", {clave: 2, arreglo: []})
})

app.post("/defGuia", urlParser, (req, res) => {
   if (req.body.btnDefProfGuia == "1") {
      //Codigo que define al profe guia
      console.log("a")
   }
   res.render("gestion.ejs", {clave: 2, arreglo: []})
})

app.post("/modEst", urlParser, (req, res) => {
   est = { id :  "1",
      nombre : "Gustavo",
      correo : "gperezb2002@gmail.com",
      cel : "86435450"
   }
   res.render("modEst.ejs", est)
})

app.post("/datosEstRes", urlParser, (req, res) => {
   estudiante = {id: req.body.entryId,
      nombre: req.body.entryName,
      correo: req.body.entryCE,
      cel: req.body.entryCel
   }
   console.log(estudiante)
   res.render("gestion.ejs", {clave: 1, arreglo: []})
})

app.post("/agrActividad", urlParser, (req, res) => {
   res.render("crearActividad.ejs")
})


var server = app.listen(3000, function () {
   var host = server.address().address
   var port = server.address().port
   
   console.log("Example app listening at http://%s:%s", host, port)
})