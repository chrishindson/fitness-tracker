import accessibleAutocomplete from 'accessible-autocomplete'

function enhance(select) {
  const originalId = select.id;
  const labelText = document.getElementById(originalId + '-label').textContent;
  const svg = `<svg data-module="accessible-select-icon" data-associated-select="${originalId}"
      class="autocomplete__dropdown-arrow-down" style="top: 8px;" viewBox="0 0 512 512">
      <path d="M256,298.3L256,298.3L256,298.3l174.2-167.2c4.3-4.2,11.4-4.1,15.8,0.2l30.6,29.9c4.4,4.3,4.5,11.3,0.2,15.5L264.1,380.9
          c-2.2,2.2-5.2,3.2-8.1,3c-3,0.1-5.9-0.9-8.1-3L35.2,176.7c-4.3-4.2-4.2-11.2,0.2-15.5L66,131.3c4.4-4.3,11.5-4.4,15.8-0.2L256,298.3  z"></path>
  </svg>`;
  accessibleAutocomplete.enhanceSelectElement({
    defaultValue: "",
    autoselect: true,
    showAllValues: true,
    confirmOnBlur: false,
    showNoOptionsFound: true,
    selectElement: select,
  });

  // Label accessiblity
  let parentDiv = select.parentNode;
  let newLabel = document.createElement("label");

  newLabel.innerHTML = labelText; //document.querySelector('[for=originalId]').innerHTML;
  newLabel.style.display = "none";
  newLabel.setAttribute("for", select.id);
  parentDiv.appendChild(newLabel);

  // Add error formatting
  let selectHasErrors = select.classList.contains("nhsuk-select--error");
  let autocompleteInput = document.getElementById(originalId);

  function handleFocusOut() {
    if (selectHasErrors) {
      autocompleteInput.classList.add("autocomplete__input--error");
    }
    let value = document.getElementById(originalId).value;
    if (value === '' || value === null) {
      select.value = '';
    }
  }

  handleFocusOut();

  autocompleteInput.addEventListener("focusout", handleFocusOut);

  // =====================================================
  // Ensure when user replaces valid answer with a non-valid answer, then valid answer is not retained
  // =====================================================
  //var parentForm = upTo(originalSelect, 'form');

  const myForm = document.querySelector("form");
  let holdSubmit = true;

  myForm.addEventListener('submit', function (e) {
    if (holdSubmit) {
      if (select.querySelectorAll('[selected]').length > 0 || select.value
        > "") {
        let selectName = select.id.replace('-select', '');
        let combo = document.getElementById(selectName);
        let resetSelect = false;
        if (select.value) {
          if (combo.value !== select.querySelector(
            'option[value="' + select.value + '"]').text) {
            resetSelect = true;
          }
        }

        if (resetSelect) {
          select.value = "";
          combo.value = "";
          if (select.querySelectorAll('[selected]').length > 0) {
            select.querySelectorAll('[selected]')[0].removeAttribute(
              'selected');
          }
        }
      }
    }
    holdSubmit = false;
  });
}

function enhanceSelect() {
  const elementNodeListOf = document.querySelectorAll(
    '[data-module="accessible-select"]');
  elementNodeListOf.forEach(enhance);
}

export {
  enhanceSelect
};