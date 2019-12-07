
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
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="../../dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="../../style/style.css">

</head>
<!--body background="../../pictures/fondo.png"-->

<div class="container-form-usuario center">

    <section class="content-header">
        <h1 class="text-center">
            <strong>${agregarusuarioi18n}</strong>
        </h1>

        <br>
    </section>


    <form method="post" class="form-horizontal" action="/usuario/crear/">
        <div class="row">

            <div class="form-group">
                <label for="username" class="control-label col-md-3">${nombreusuarioi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="username" class="form-control" required placeholder="Nombre de usuario...">
                </div>

            </div>


            <div class="form-group">
                <label for="password" class="control-label col-md-3">${passwordusuarioi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="password" name="password" class="form-control" required placeholder="Password...">
                </div>

            </div>


            <div class="form-group">
                <label for="idRoles" class="control-label col-md-3">${rolusuarioi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <select name="idRoles" class="form-control" id="idRoles" required>
                        <#list roles as rol >
                            <option value="${rol.id}">${rol.role}</option>
                        </#list>
                    </select>
                </div>

            </div>


            <div class="form-group">
                <button class="btn btn-primary col-md-offset-5" type="submit">${botonguardari18n}</button>
                <a class="btn btn-danger" href="/usuario/" role="button">${botoncancelari18n}</a>
            </div>


        </div>

    </form>

</div>

</body>
</html>

