import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '7e24fd805884bb21e083677a5d0b2331dd87637e437c4fb007e8a393b94d5914') {
    pending.push(import('./chunks/chunk-49e51f3b7eaccb069bd8be4ce370b85c90e20c89f0b0e18dac221340c199f4c6.js'));
  }
  if (key === 'f36019cd2b46c9bb5481e1b1bfce8f0cd2c5266d17cbd59f6001749a2a4266f6') {
    pending.push(import('./chunks/chunk-49e51f3b7eaccb069bd8be4ce370b85c90e20c89f0b0e18dac221340c199f4c6.js'));
  }
  if (key === 'feb0fded62c1c2a63897b0ac3873418929af62787c71e8cd28dfb4d44b9b87e7') {
    pending.push(import('./chunks/chunk-49e51f3b7eaccb069bd8be4ce370b85c90e20c89f0b0e18dac221340c199f4c6.js'));
  }
  if (key === 'cb440b26ddbecf8c5874e5ea94e80bc3cdb5fc807c1ec085962394e4492c7d69') {
    pending.push(import('./chunks/chunk-640c5d5ebbdb78315bd2f9c0f79525cb2294d76a77feafaa7fa1f6f745b12a2a.js'));
  }
  if (key === '7f33d4e87c7326e662482208e031bfb4c65c608fddab3379963eb305407ada10') {
    pending.push(import('./chunks/chunk-313dbe644244358b3ad8ac2903380c5c2332d2f88c0d9f5603f58754aeb333e3.js'));
  }
  if (key === 'c4d7c8562551499c146b4f6daf448baf4684e7540f4f4cf49f6fac02426d9c10') {
    pending.push(import('./chunks/chunk-d25c363604782aa0216630af1f120d039300d292055e3a7d6bbed5b2aeb8678d.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;