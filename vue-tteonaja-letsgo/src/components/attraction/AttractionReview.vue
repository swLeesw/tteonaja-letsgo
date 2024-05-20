<script setup>

import { onMounted, ref, watch } from 'vue';
import { useRoute, onBeforeRouteUpdate } from 'vue-router';
import { listReview } from "@/api/attractionReview";

onBeforeRouteUpdate((to) => {
    attractionId.value = ref(route.params.id);
    getReview();
})

const route = useRoute();
const reviews = ref([]);
const attractionId = ref(route.params.id);


onMounted(() => {
    console.log("Review.onMount")

    console.log(route.params);
    console.log(attractionId.value);
    console.log(route.params.id);
    getReview();
})

const getReview = () => {
    listReview(
        attractionId.value,
        ({ data }) => {
            reviews.value = data;
            console.log(reviews.value.reviewTitle)
        },
        (error) => {
            console.log(error);
        }
    )
}


</script>

<template>
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasScrollingLabel">리뷰</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <p>Try scrolling the rest of the page to see this option in action.</p>
    </div>
    <div v-for="(review, index) in reviews" :key="index">
        {{ review.reviewTitle }}
    </div>
</template>

<style scoped></style>