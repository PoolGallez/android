# AM309_Retrofit2_Upload

In questa esercitazione lavoreremo
- Retrofit 2: [qui](http://square.github.io/retrofit/).
- OkHttp: [qui](http://square.github.io/okhttp/).  

## Interfaccia per il service di Retrofit 2

Come da documentazione di retrofit le `Part` vengono dichiarate mediante l'annotation `Part`, ecco un esempio dalla documetazione ufficiale.
```
@Multipart
@PUT("user/photo")
Call<User> updateUser(@Part("photo") RequestBody photo, @Part("description") RequestBody description);
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







## Il server

in questa versione provvisoria il server è un semplice file php
```
<?php  
    $file_path = "";
    $var = $_POST['result'];
    $file_path = $file_path . basename( $_FILES['uploaded_file']['name']);
    if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {
        $result = array("result" => "success", "value" => $var);
    } else{
        $result = array("result" => "error");
    }
    echo json_encode($result);
?>
```
il comando
```
$ php -S localhost:8080
```
