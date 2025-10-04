<%-- 
    Document   : formulario
    Created on : 23 de set. de 2025, 18:36:38
    Author     : Samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Primeiro Formulario</title>
        <style>
            .alinharDireita{
                text-align: right
            }
            
        </style>
    </head>
    <body>
        <table class="alinharDireita">
            <tr>
                <th>
                    Nome:
                </th>
                <th>
                    ${requestScope.CadastroObtido.nome}
                </th>
            </tr>
            <tr>
                <th>
                    Sobrenome:
                </th>
                <th>
                    ${requestScope.CadastroObtido.sobrenome}
                </th>
            </tr>
            <tr>
                <th>
                    CPF:
                </th>
                <th>
                    ${requestScope.CadastroObtido.CPF}
                </th>
            </tr>
            <tr>
                <th>
                    Data:
                </th>
                <th>
                    ${requestScope.CadastroObtido.data}
                </th>
            </tr>
            <tr>
                <th>
                    Sexo:
                </th>
                <th>
                    ${requestScope.CadastroObtido.sexo}
                </th>
            </tr>
            <tr>
                <th>
                    Logradouro:
                </th>
                <th>
                    ${requestScope.CadastroObtido.logradouro}
                </th>
            </tr>
            <tr>
                <th>
                    Número:
                </th>
                <th>
                    ${requestScope.CadastroObtido.numero}
                </th>
            </tr>
            <tr>
                <th>
                    Complemento:
                </th>
                <th>
                    ${requestScope.CadastroObtido.complemento}
                </th>
            </tr>
            <tr>
                <th>
                    Cidade:
                </th>
                <th>
                    ${requestScope.CadastroObtido.cidade}
                </th>
            </tr>
            <tr>
                <th>
                    CEP:
                </th>
                <th>
                    ${requestScope.CadastroObtido.CEP}
                </th>
            </tr>
            <tr>
                <th>
                    Data:
                </th>
                <th>
                    ${requestScope.CadastroObtido.filhos}
                </th>
            </tr>
            <tr>
                <th>
                    Observação:
                </th>
                <th>
                    ${requestScope.CadastroObtido.obs}
                </th>
            </tr>
        </table>
        
        
    </body>
</html>
