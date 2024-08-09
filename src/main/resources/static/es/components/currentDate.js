function enhance(element) {
  const originalId = element.id.replace('.today', '');

  element.addEventListener('click', function (e) {
    let today = new Date();
    document.getElementById(originalId + '.day').value = today.getDate();
    document.getElementById(originalId + '.month').value = today.getMonth() + 1;
    document.getElementById(originalId + '.year').value = today.getFullYear();
  });
}

function currentDate() {
  const elementNodeListOf = document.querySelectorAll(
    '[data-module="current-date"]');
  elementNodeListOf.forEach(enhance);
}

export {
  currentDate
};