function registerUser(user) {
  const url = "http://localhost:8085/user";
  let request = new XMLHttpRequest();
  request.open("POST", url, true);
  request.setRequestHeader("Content-type", "application/json");

  try {
    request.send(JSON.stringify(user));
  } catch (err) {
    alert(`Erro ao criar o usuario ${err}`);
  }
}

function verifyUser() {
  event.preventDefault();
  const name = document.getElementById("name").value;
  const cargo = document.getElementById("Cargo").value;
  const telefone = document.getElementById("Telefone").value;
  const departamento = document.getElementById("Departamento").value;
  const email = document.getElementById("email").value;
  const pass = document.getElementById("pass").value;
  const pass_confirm = document.getElementById("pass-confirm").value;

  const user = {
    nome: name,
    cargo: cargo,
    telefone: telefone,
    departamento: departamento,
    email: email,
    senha: pass,
  };

  console.log(user);

  if (pass !== pass_confirm) {
    alert("As senhas precisam ser iguais");
  }

  if (
    name === null ||
    cargo === null ||
    telefone === null ||
    departamento === null ||
    email === null ||
    pass === null ||
    pass_confirm === null
  ) {
    alert("Todos os campos precisam ser preenchidos");
  }

  registerUser(user);
}
