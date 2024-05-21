<script setup>

import { onMounted, ref } from 'vue';
import { useRouter, useRoute, onBeforeRouteUpdate } from 'vue-router';
import { listReview, likeReview } from "@/api/attractionReview";
import { jwtDecode } from "jwt-decode";
import Swal from 'sweetalert2';


const router = useRouter();
const route = useRoute();
//리뷰들
const reviews = ref([]);
const attractionId = ref(route.params.id);

onBeforeRouteUpdate((to) => {
    console.log("onBeforeRouteUpdate = " + to.params.id);
    attractionId.value = to.params.id;
    getReview();
})

onMounted(() => {
    console.log("reviewMounte" + route.params.id);
    getReview();
})

const moveWrite = () => {
    console.log("moveWrite : " + attractionId.value);
    router.push({ name: "map-review-write", params: { id: attractionId.value } });
}

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

const likeReviewf = (param) => {
    let token = sessionStorage.getItem("accessToken");

    if (token == null) {
        return;
    }

    let decodeToken = jwtDecode(token);

    console.log(param);
    likeReview(
        param, decodeToken.userId,
        ({ data }) => {
            if (data.check) {
                Swal.fire({
                    position: "top",
                    icon: "error",
                    title: "좋아요 취소.",
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                Swal.fire({
                    position: "top",
                    icon: "success",
                    title: "좋아요.",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
            getReview();
        },
        (error) => {
            console.log(error);
        }
    )
}


</script>

<template>
    <div class="offcanvas-body bodyy">
        <div class="review" v-for="(review, index) in reviews" :key="index">
            <div>
                <strong>{{ review.reviewTitle }}</strong>
            </div>
            <div>
                {{ review.content }}<br>
            </div>
            {{ review.registerTime }}<br>
            {{ review.reviewLike }}<br>
            <button class="btn btn-custom" @click="likeReviewf(review.reviewNo)">좋아요</button>
            <hr class="border border-1 opacity-50">
        </div>
    </div>
</template>

<style scoped>
.review {
    margin: 10px;

}

.btn-custom {
    background-color: rgba(67, 180, 255, 0.158);
    color: #888686;
    border: 1px solid;
    border-color: #8886861a;
}
</style>