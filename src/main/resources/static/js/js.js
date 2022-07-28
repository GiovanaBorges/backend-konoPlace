function validar() {
  const name = document.getElementById("name").value;
  const email = document.getElementById("email").value;
  const cargo = document.getElementById("job").value;
  const departamento = document.getElementById("departamento").value;
  const telefone = document.getElementById("phone").value;
  const senha = document.getElementById("pass").value;

  if (
    email === "" ||
    email === null ||
    name === "" ||
    name === null ||
    cargo === "" ||
    cargo === null ||
    departamento === "" ||
    departamento === null ||
    telefone === "" ||
    telefone === null ||
    senha === "" ||
    senha === null
  ) {
    alert("Não pode haver espaços vazios");
  }
}

function validarcadastro() {
  const name = document.getElementById("name").value;
  const email = document.getElementById("email").value;
  const cargo = document.getElementById("cargo").value;
  const departamento = document.getElementById("departamento").value;
  const telefone = document.getElementById("phone").value;
  const senha = document.getElementById("pass").value;
  const confirmarsenha = document.getElementById("pass-confirm").value;

  if (
    email === "" ||
    email === null ||
    name === "" ||
    name === null ||
    cargo === "" ||
    cargo === null ||
    departamento === "" ||
    departamento === null ||
    telefone === "" ||
    telefone === null ||
    senha === "" ||
    senha === null ||
    confirmarsenha === "" ||
    confirmarsenha === null
  ) {
    alert("Não pode haver campos vazios");
  }

  if (senha !== confirmarsenha) {
    alert("Senhas incompatíveis");
  }
}

function PreviewImage() {
  let image =  document.getElementById("uploadImage").value

    document.getElementById("uploadPreview").src = image;
}

function date() {
  let data = document.getElementById("date_reservation").value;
  let dividir = data.split("-");
  let ano = dividir[0];
  let mes = dividir[1];
  let dia = dividir[2];
  alert(`dia : ${dia} ,mes : ${mes} ,ano : ${ano}`);

}

function showData(data){
    console.log(data.length)
    data.filter(reserva => reserva.length == 0 ? 
        (
            document.querySelector(".reserve_space").innerHTML += `
            <div class="d-flex align-items-center align-content-center mt-5 ">
              <div class="card m-auto" style="width: 18rem;">
                <div class="card-body">
                  <h5 class="card-title" id="reserve_information">Reservas vazias</h5>
                  <p class="card-text mt-1" id="date_reserve">Você ainda não criou nenhuma reserva</p>
                  <a href="./home.html" class="btn btn-outline-success" style="width:100%;">Criar reserva</a>
                </div>
              </div>
            </div>
            `
        ) :
    (
        document.querySelector(".reserve_space").innerHTML += `
        <div class="d-flex align-items-center align-content-center mt-5 ">
          <div class="card m-auto" style="width: 18rem;">
            <div class="card-body">
              <h5 class="card-title" id="reserve_information">Mesa : ${reserva.mesa.name}</h5>
              <p class="card-text mt-1" id="date_reserve">Reserva : ${reserva.date}</p>
              <p class="card-text" id="perifericos">Periféricos: ${reserva.mesa.perifericos}</p>
              <a href="./home.html" class="btn btn-outline-success" style="width:100%;">Ver informações</a>
              <div class="d-flex mt-3">
                <a href="#" data-bs-toggle="modal" data-bs-target="#modalEditReserva" class="btn btn-outline-primary me-1"
                  style="width:50%;">Editar</a>
                <a href="#" data-bs-toggle="modal" data-bs-target="#modalDeleteReserva" class="btn btn-outline-danger"
                  style="width:50%;">Cancelar</a>
              </div>
    
            </div>
          </div>
        </div>
        `
    )
    ) 
}




/*

function handleMesa(id){
    let encript = require('BCryptPasswordEncoder');
    let url = 'localhost';
    let user = 'root';
    let pass = 'root';

    let headers = new Headers();

    //headers.append('Content-Type', 'text/json');
    headers.append('Authorization', 'Basic' + encript.encode(username + ":" + password));

        fetch(url, {method:'GET',
        headers: headers,
        //credentials: 'user:passwd'
        })
        .then(response => response.json())
        .then(json => console.log(json));

}

function writeMesa(){

}

*/
