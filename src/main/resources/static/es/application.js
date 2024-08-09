import './jquery.js'
import $ from 'jquery';
import jQuery from 'jquery';
import {enhanceSelect} from "./components/autocomplete";
import {currentDate} from "./components/currentDate";
import Checkboxes from "../../../../../node_modules/nhsuk-frontend/packages/components/checkboxes/checkboxes";
import Details from "../../../../../node_modules/nhsuk-frontend/packages/components/details/details";
import ErrorSummary from "../../../../../node_modules/nhsuk-frontend/packages/components/error-summary/error-summary";
import Header from "../../../../../node_modules/nhsuk-frontend/packages/components/header/header";
import Radios from "../../../../../node_modules/nhsuk-frontend/packages/components/radios/radios";
import SkipLink from "../../../../../node_modules/nhsuk-frontend/packages/components/skip-link/skip-link";
import Tabs from "../../../../../node_modules/nhsuk-frontend/packages/components/tabs/tabs";
import CharacterCount
  from "../../../../../node_modules/nhsuk-frontend/packages/components/character-count/character-count";

window.$ = $;
window.jQuery = jQuery;
(() => {
  'use strict';

  function ready(fn) {
    if (document.readyState !== 'loading') {
      fn();
    } else {
      document.addEventListener("DOMContentLoaded", fn);
    }
  }

  ready(() => {
    Checkboxes();
    Details();
    ErrorSummary();
    Header();
    Radios();
    SkipLink();
    Tabs();
    CharacterCount();
    enhanceSelect();
    currentDate();
  });
})();