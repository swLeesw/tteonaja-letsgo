<script setup>

</script>

<template>
  <div>
    <h2>appvue</h2>
    <router-link :to="{ name: 'home' }">home</router-link>
    <RouterView />
  </div>
</template>

<style scoped></style>