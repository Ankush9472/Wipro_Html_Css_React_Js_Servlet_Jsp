<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product</title>

    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">

    <div class="alert alert-success text-center">
        <%= request.getAttribute("msg") %>
    </div>

    <h2 class="text-center mb-4">Product Dashboard</h2>

    <div class="row">

        <div class="col-md-4">
            <div class="card p-3 shadow">
                <h5>Laptop</h5>
                <p>Price: 30000</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card p-3 shadow">
                <h5>Mobile</h5>
                <p>Price: 9999</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card p-3 shadow">
                <h5>Headphones</h5>
                <p>Price: 1999</p>
            </div>
        </div>

    </div>

</div>

</body>
</html>