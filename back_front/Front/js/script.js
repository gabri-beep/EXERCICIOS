/* var myImage = document.querySelector("img");

fetch("flowers.jpg")
  .then(function (response) {
    return response.blob();
  })
  .then(function (myBlob) {
    var objectURL = URL.createObjectURL(myBlob);
    myImage.src = objectURL;
  }); */

  const response = await fetch("localhost:8080/produto", {
    method: "POST",
    body: JSON.stringify({ username: "example" }),
    // ...
  });
  
  