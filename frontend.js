const rootApp = document.getElementById("root");

rootApp.innerHTML = `
  <button id="toggleButton">OFF</button>
`;

document.getElementById("toggleButton").addEventListener("click", function () {
  const button = this;
  button.textContent = button.textContent === "ON" ? "OFF" : "ON";
});
