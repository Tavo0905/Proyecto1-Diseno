const http = require('http');
var express = require('express')
var bodyParser = require('body-parser')
var app = express()
var urlParser = bodyParser.urlencoded({extended: false})
var usuario = {user: "", password: ""}

app.use(express.static('views'))

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

   //res.render('selModulo.ejs');
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

   const req = http.request(options, (res) => {
   let responseData = '';

  res.on('data', (chunk) => {
    responseData += chunk;
  });

  res.on('end', () => {
   const estudiantes = JSON.parse(responseBody);
   res.render("gestion.ejs", {clave: 1,arreglo : estudiantes });
  });
});

req.on('error', (error) => {
  console.error(error);
});

req.write(data);
req.end();})

app.post('/gestionarProf', urlParser, (req, res) => {
   const options = {
      hostname: 'localhost',
      port: 8080,
      path: '/gestionarProf',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      }
    };

    
   const data = JSON.stringify({ clave: 2, arreglo: [], user: usuario.user});
   options.headers['Content-Length'] = data.length;

      const request = http.request(options, (response) => {
         let responseBody = '';

         response.on('data', (chunk) => {
            responseBody += chunk;
         });

         response.on('end', () => {
            const profesores = JSON.parse(responseBody);
            res.render("gestion.ejs", { clave: 2, arreglo : profesores });
         });
      });

      request.on('error', (error) => {
         console.error('Error al realizar la solicitud:', error);
         res.status(500).send('Error interno del servidor');
      });

      request.write(data);
      request.end();
  
      res.render("gestion.ejs", {clave: 2, arreglo: []})
})

app.post('/gestionarGuias', urlParser, (req, res) => {
   res.render("gestion.ejs", {clave: 3, arreglo: []})
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
   console.log(profe)
   res.render("gestion.ejs", {clave: 2, arreglo: []})
})

app.post("/bajaProf", urlParser, (req, res) => {
   if (req.body.btnBajaProfGuia == "1") {
      //Codigo que elimina al profe guia
      console.log("a")
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

var server = app.listen(3000, function () {
   var host = server.address().address
   var port = server.address().port
   
   console.log("Example app listening at http://%s:%s", host, port)
})