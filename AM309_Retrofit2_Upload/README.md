# AM309_Retrofit2_Upload

In questa esercitazione lavoreremo
- Retrofit 2: [qui](http://square.github.io/retrofit/).
- OkHttp: [qui](http://square.github.io/okhttp/).  

## Interfaccia per il service di Retrofit 2

Come da documentazione di retrofit le `Part` vengono dichiarate mediante l'annotation `Part`, ecco un esempio dalla documetazione ufficiale.
```
@Multipart
@PUT("user/photo")
Call<User> updateUser(
  @Part("photo") RequestBody photo,
  @Part("description") RequestBody description
);
```
un altro esempio dalle [api](http://square.github.io/retrofit/2.x/retrofit/)
```
@Multipart
 @POST("/")
 Call<ResponseBody> example(
     @Part("description") String description,
     @Part(value = "image", encoding = "8-bit") RequestBody image);
```
nel nostro caso lavoriamo con una sottoclasse di `RequestBody`
```
@Multipart
@POST("upload")
Call<ResponseBody> upload(
        @Part("description") RequestBody description,
        @Part MultipartBody.Part file
);
```
`RequestBody` è classe di `OkHttp` e rimandiamo alla [api](http://square.github.io/okhttp/3.x/okhttp/), è classe **abstract** e a noi interessa in particolare la sua derivata `MultipartBody` con le classi statiche `MultipartBody.Part` per riferirce alla `@Part` e `MultipartBody.Builder` per costruirla.

## Pick Up a file

Da Kitkat è a disposizione il **ContentProvider** per i documenti salvati; qui sotto alcuni riferimenti per lo studio:
- [Content Provider Basics](https://developer.android.com/guide/topics/providers/content-provider-basics.html),
- [Open Files using Storage Access Framework](https://developer.android.com/guide/topics/providers/document-provider.html).
- API per `MediaStore` [qui](https://developer.android.com/reference/android/provider/MediaStore.html).

`ContentResolver` permette l'accesso al content model e di lì lavoriamo come mostrato nel codice, si veda ad esempio il recupero del **path reale** del file mediante l'uso del **SAF** (Storage Access Framework).

## Retrofit Request

`@Part` si riferisce alla singola part, come da api in retrofit
> * If the type is MultipartBody.Part the contents will be used directly. Omit the name from the annotation (i.e., @Part MultipartBody.Part part).  
* If the type is RequestBody the value will be used directly with its content type. Supply the part name in the annotation (e.g., @Part("foo") RequestBody foo).  
* Other object types will be converted to an appropriate representation by using a converter. Supply the part name in the annotation (e.g., @Part("foo") Image photo).

Dalla api di okhttp3 abbiamo che
```
public static RequestBody create(@Nullable
                                 MediaType contentType,
                                 File file)

Returns a new request body that transmits the content of file.
```
A questo punto abbiamo due possibilità (tralasciando il caso generico).

### create from data: RequestBody

Partire da dati grezzi tipo `RequestBody` (classe astratta), come nel nostro caso
```
public static MultipartBody.Part createFormData(String name,
                                                @Nullable
                                                String filename,
                                                RequestBody body)
```

### create from data: String

```
public static MultipartBody.Part createFormData(String name,
                                                String value)
```

Ecco come procediamo.

```
MultipartBody.Part body =
    MultipartBody.Part.createFormData("image", file.getName(), requestFile);
```
non direi in questo modo ...
```
RequestBody description =  MultipartBody.Part.createFormData("descriptipon",
    "image uploaded");
```
in alternativa
```
public static RequestBody create(@Nullable
                                 MediaType contentType,
                                 String content)
```
specificando
```
MultipartBody.FORM
```
> The media-type **multipart/form-data** follows the rules of all multipart MIME data streams as outlined in RFC 2046.

## Permissions

Da Android 6 vengono gestiti i permessi **runtime**, si veda [qui](https://developer.android.com/training/permissions/requesting.html#java). Qui sotto il nostro metodo
```
// from API 23 ....
    public boolean enabledPermission() {
        if (Build.VERSION.SDK_INT >= 23) { // from Android 6.0
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {
            return true;
        }
    }
```
in `onCreate()`
```
// check permissions
        if(!enabledPermission())
            Toast.makeText(this, getString(R.string.permissions), Toast.LENGTH_LONG).show();
```





## Il server

in questa versione abbiamo usato il nostro server node; per installare i moduli
```
npm install
```
quindi lanciare il servere con
```
node app.js
```
qui sotto una parte del server
```
app.post('/upload', upload.single('image'), function(req, res) {
  // req.file is the `photo` file
  // req.body will hold the text fields, if there were any
  console.log('req.body: ' + req.body['description']);
  res.redirect('/')
})
```
