<script setup>
import AttractionSearch from "@/components/attraction/AttractionSearch.vue"
import { useRoute } from "vue-router"

const route = useRoute();

console.log(route.params.attractionName);

</script>

<template>
  <AttractionSearch />
</template>

<style scoped></style>