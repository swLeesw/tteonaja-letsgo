<script setup>
import HeaderNavigationBar from "@/components/layout/HeaderNavigationBar.vue"
import FooterBar from "@/components/layout/FooterBar.vue"
import HomeView from "@/views/HomeView.vue"
</script>

<template>
  <div class="header-main min-vh-100">
    <Header>
      <HeaderNavigationBar />
    </Header>
    <main>
      <!-- <HomeView /> -->
      <RouterView />
    </main>
  </div>
</template>

<style scoped>
.footer-bar {}
</style>
