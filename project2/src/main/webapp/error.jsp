<!DOCTYPE html>
<html>
<head>
    <title>Error</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container mt-5">

    <div class="alert alert-danger text-center">
        <%= request.getAttribute("error") %>
    </div>

    <div class="text-center">
        <a href="login.jsp" class="btn btn-primary">Back to Login</a>
    </div>

</div>

</body>
</html>