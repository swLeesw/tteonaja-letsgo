<script setup>

import { onMounted, ref } from 'vue';
import { useRouter, useRoute, onBeforeRouteUpdate } from 'vue-router';
import { listReview, likeReview, deleteReview } from "@/api/attractionReview";
import { jwtDecode } from "jwt-decode";
import Swal from 'sweetalert2';


const router = useRouter();
const route = useRoute();
//리뷰들
const reviews = ref([]);
const attractionId = ref(route.params.id);

//토큰에서 id정보 저장
const userId = ref(null);

onBeforeRouteUpdate((to) => {
    console.log("onBeforeRouteUpdate = " + to.params.id);
    attractionId.value = to.params.id;
    getReview();
})

onMounted(() => {
    console.log("reviewMounte" + route.params.id);
    getReview();

    let token = sessionStorage.getItem("accessToken");
    if (token == null) {
        userId.value = null;
        return;
    }

    let decodeToken = jwtDecode(token);
    userId.value = decodeToken.userId;
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

const likeReviewf = (reviewNo) => {
    let token = sessionStorage.getItem("accessToken");

    if (token == null) {
        return;
    }

    let decodeToken = jwtDecode(token);

    console.log(reviewNo);
    likeReview(
        reviewNo, decodeToken.userId,
        ({ data }) => {
            if (data.check) {
                Swal.fire({
                    position: "top",
                    icon: "error",
                    title: "좋아요 취소",
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                Swal.fire({
                    position: "top",
                    icon: "success",
                    title: "좋아요",
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

const deleteReviewf = (param) => {
    const delCheck = ref(false);
    Swal.fire({
        title: "정말 삭제하시겠습니까?",
        icon: "question",
        showCancelButton: true,
        confirmButtonText: "확인",
        cancelButtonText: `취소`
    }).then((result) => {
        if (result.isConfirmed) {
            delCheck.value = true;
            deleteReview(
                param,
                () => {
                    getReview();
                },
                (error) => {
                    console.log(error);
                }
            )
            Swal.fire("삭제되었습니다!", "", "success");
        }
    });

}


</script>

<template>
    <div class="offcanvas-body">
        <div class="review" v-for="(review, index) in reviews" :key="index">
            <div class="review-item"
                style="border: 0px solid #ddd; padding: 20px; border-radius: 10px; margin-bottom: 20px;">
                <div>
                    <div>
                        <p><strong style="font-size: 1.1em; color: #333;">{{ review.reviewTitle }}</strong></p>
                    </div>
                    <button style="margin-left: 152px;" class="btn btn-sm btn-outline-danger"
                        v-if="userId == review.userId" @click="deleteReviewf(review.reviewNo)">삭제</button>
                </div>
                <!-- <div style="display: flex; justify-content: space-between; align-items: center;">
                    <div>
                        <p><strong style="font-size: 1.2em; color: #333;">{{ review.reviewTitle }}</strong></p>
                    </div>
                    <button style="" class="btn btn-sm btn-outline-danger" v-if="userId == review.userId"
                        @click="deleteReviewf(review.reviewNo)">삭제</button>
                </div> -->
                <div style="margin-top: 10px;">
                    <p style="font-size: 1em; color: #666;">{{ review.content }}</p>
                </div>
                <div style="margin-top: 10px;">
                    <p style="font-size: 0.9em; color: #999;">{{ review.registerTime }}</p>
                </div>
                <div style="margin-top: 10px;">
                    <p style="font-size: 1em; color: #007bff;">좋아요 : {{ review.reviewLike }}</p>
                </div>
                <div style="margin-top: 10px;">
                    <p style="font-size: 1em; color: #555;">작성자 : {{ review.userId }}</p>
                </div>
                <div style="margin-top: 10px;">
                    <button style="margin-left: 152px;" class="btn btn-outline-danger"
                        @click="likeReviewf(review.reviewNo)">♥</button>
                </div>
                <hr class="border border-1 opacity-50" style="margin-top: 20px;">
            </div>
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

.btn-custom:hover {
    background-color: rgba(67, 180, 255, 0.623);
    color: #888686;
}

.btn-custom:active {
    background-color: rgba(67, 180, 255, 0.027);
    color: #888686;
}
</style>