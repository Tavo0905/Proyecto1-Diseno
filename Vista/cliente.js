var express = require('express');
var app = express();

app.use(express.static('views'));

app.get('/', (req, res) => {
   res.render('prueba.ejs');
})

app.post('/validarDatos', (req, res) => { // Validar datos del login
   usuario = {
      user : req.body.username,
      password : req.body.password
  }
  console.log(usuario["user"], usuario["password"])
  //validarUsuario(usuario, res)
  res.redirect('prueba.ejs')
})

var server = app.listen(3000, function () {
   var host = server.address().address
   var port = server.address().port
   
   console.log("Example app listening at http://%s:%s", host, port)
})