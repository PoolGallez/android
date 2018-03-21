var express = require('express')
var fs = require('fs')
var multer  = require('multer') // https://github.com/expressjs/multer
var upload = multer({ dest: 'uploads/' })

/* rename files
var storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, 'uploads/')
  },
  filename: function (req, file, cb) {
    cb(null, file.fieldname + '-' + Date.now() + '.jpeg')
  }
})
var upload = multer({ storage: storage })
*/

var app = express()
app.use(express.static('uploads'))
app.set('view engine', 'ejs')

// index.ejs in vews folder
app.get('/', function (req, res) {
  fs.readdir('uploads/', function(err, files){
    /*
    files.forEach(function(file){
      console.log(file);
    });
    */
    console.log(files);
    res.render('index', { title: 'Images', images: files})
  })
})

app.post('/upload', upload.single('image'), function(req, res) {
  // req.file is the `photo` file
  // req.body will hold the text fields, if there were any
  console.log('req.body: ' + req.body['description']);
  res.redirect('/')
})

app.listen(3000, function () {
  console.log('A simple gallery\n Press Ctrl+C to terminate')
})
