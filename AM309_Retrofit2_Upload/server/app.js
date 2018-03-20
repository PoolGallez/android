var express = require('express')
var fs = require('fs')
var multer  = require('multer')
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
    res.render('index', { title: 'Gallery', photos: files})
  })
})

app.get('/upload', function (req, res) {
  res.render('upload', { title: 'Umpload'})
})

app.post('/upload', upload.single('photo'), function(req, res) {
  // req.file is the `photo` file
  // req.body will hold the text fields, if there were any
  console.log('upload file');
  res.redirect('/')
})

app.listen(3000, function () {
  console.log('A simple gallery\n Press Ctrl+C to terminate')
})
