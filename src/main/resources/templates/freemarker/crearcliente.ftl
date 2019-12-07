
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <title> ${titulo}</title>

    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../dist/css/AdminLTE.min.css">

    <link rel="stylesheet" href="../../style/style.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="../../dist/css/skins/skin-blue.min.css">

</head>
<!--body background="../../pictures/fondo.png"-->


<div class="container-form center">

    <section class="content-header">
        <h1 class="text-center">
            <strong>${agregarclientei18n}</strong>
        </h1>

        <br>
    </section>


<!--El enctype es lo unico necesario para hacer funcionar la subida de imagenes, ademas de que debemos de especificar
 el input de foto como file-->
    <form method="post" class="form-horizontal" action="/cliente/crear/" enctype="multipart/form-data">
        <div class="row">

            <div class="form-group">
                <label for="nombre" class="control-label col-md-3">${nombreclientei18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="nombre" class="form-control" required placeholder="Nombre...">
                </div>

            </div>



            <div class="form-group">
                <label for="apellido" class="control-label col-md-3">${apellidoclientei18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="apellido" class="form-control " placeholder="Apellido...">
                </div>

            </div>



            <div class="form-group">
                <label for="cedula" class="control-label col-md-3">${cedulaclientei18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="cedula" class="form-control" required placeholder="Cedula...">
                </div>

            </div>



            <div class="form-group">
                <label for="telefono" class="control-label col-md-3">${telefonoclientei18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="telefono" class="form-control" placeholder="Telefono...">
                </div>

            </div>


            <div class="form-group">
                <label for="direccion" class="control-label col-md-3">${direccionclientei18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="direccion" class="form-control" placeholder="Direccion...">
                </div>

            </div>


            <div class="form-group">
                <label for="file" class="control-label col-md-3">${fotoclientei18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="file" name="files" class="form-control" required placeholder="Foto del cliente...">
                </div>

            </div>



            <div class="form-group">
                <button class="btn btn-primary col-md-offset-5" type="submit">${botonguardari18n}</button>
                <a class="btn btn-danger" href="/cliente/" role="button">${botoncancelari18n}</a>
            </div>


        </div>


    </form>


</div>

</body>
</html>

