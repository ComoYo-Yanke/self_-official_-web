<template>
  <div id="app">
    <HomeView />
  </div>
</template>

<script setup>
import HomeView from './views/HomeView.vue';
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: Arial, sans-serif;
}

body {
  font-family: 'Inter', -apple-system, BlinkMacSystemFont, sans-serif;
  line-height: 1.6;
  color: #2d3748;
  background: #f7fafc;
}

#app {
  min-height: 100vh;
}
</style>