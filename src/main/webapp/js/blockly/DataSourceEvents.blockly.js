window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.DataSourceEvents = window.blockly.js.blockly.DataSourceEvents || {};

/**
 * DataSourceEvents
 */
window.blockly.js.blockly.DataSourceEvents.notifySuccess = function() {
 var item;
  this.cronapi.screen.notify('success','Informações atualizadas com sucesso');
}
