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
   res.render("gestion.ejs", {clave: 1, arreglo: []})
})

app.post('/gestionarProf', urlParser, (req, res) => {
   res.render("gestion.ejs", {clave: 2, arreglo: []})
})


var server = app.listen(3000, function () {
   var host = server.address().address
   var port = server.address().port
   
   console.log("Example app listening at http://%s:%s", host, port)
})