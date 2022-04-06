<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Autenticação</title>
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" ></script>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card border-0 shadow rounded-3 my-5">
                <div class="card-body p-4 p-sm-5">

                    <%
                        if(session.getAttribute("erro") != null){
                            String erro = (String) session.getAttribute("erro");
                    %>
                    <div class="alert alert-danger" role="alert"><%=erro%></div>
                    <%
                        }
                        session.removeAttribute("erro");
                    %>



                    <h5 class="card-title text-center mb-5 fw-light fs-5">Cadastrar</h5>
                    <form method="post" action="verificar">


                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="floatingInput" name="email" placeholder="name@example.com">
                            <label for="floatingInput">e-mail</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingId" name="nome" placeholder="Nome">
                            <label for="floatingInput">Nome</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="floatingPassword" name="senha" placeholder="Senha">
                            <label for="floatingPassword">senha</label>
                        </div>

                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" value="" id="rememberPasswordCheck">
                            <label class="form-check-label" for="rememberPasswordCheck">
                                Lembrar
                            </label>
                        </div>

                        <div class="d-grid">
                            <button class="btn btn-primary btn-login text-uppercase fw-bold" type="submit">Cadastrar</button>
                        </div>

                    </form>


                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
