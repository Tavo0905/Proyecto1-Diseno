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
  //validarUsuario(usuario, res)
  res.render('selModulo.ejs')
})

app.post('/gestionarEst', urlParser, (req, res) => {
   res.render("gestion.ejs", {clave: 1})
})

app.post('/gestionarProf', urlParser, (req, res) => {
   res.render("gestion.ejs", {clave: 2})
})


var server = app.listen(3000, function () {
   var host = server.address().address
   var port = server.address().port
   
   console.log("Example app listening at http://%s:%s", host, port)
})