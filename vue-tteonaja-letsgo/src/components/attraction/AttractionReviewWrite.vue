<script setup>
import { onMounted, ref } from 'vue';
import { useRouter, useRoute, onBeforeRouteUpdate } from 'vue-router';
import { registReview } from "@/api/attractionReview";
import { jwtDecode } from "jwt-decode";
import Swal from 'sweetalert2';

const router = useRouter();
const route = useRoute();
const attractionId = ref(route.params.id);

// const userId = ref("");
// const reviewTitle = ref("");
// const content = ref("");

const attractionReviewSave = ref({
    userId: "",
    reviewTitle: "",
    content: "",
    attractionId: attractionId.value
})

onMounted(() => {
    let token = sessionStorage.getItem("accessToken");

    if (token == null) {
        return;
    }

    let decodeToken = jwtDecode(token);

    attractionReviewSave.value.userId = decodeToken.userId;

})

const registReviewf = () => {
    registReview(
        attractionReviewSave.value,
        () => {
            Swal.fire({
                position: "top",
                icon: "success",
                title: "성공적으로 등록되었습니다!",
                showConfirmButton: false,
                timer: 1500
            });
            router.push({ name: 'map-review', params: { id: attractionId.value } })
        },
        (error) => {
            console.log(error);
        }
    )
}

</script>

<template>
    <h4 class="d-flex justify-content-center mb-4" style="color: #888686;"> 리뷰 작성</h4>
    <form @submit.prevent="registReviewf">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">아이디</span>
            <input type="text" class="form-control" v-model="attractionReviewSave.userId" readonly aria-label="Username"
                aria-describedby="basic-addon1">
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">제목</span>
            <input type="text" class="form-control" v-model="attractionReviewSave.reviewTitle" placeholder="제목을 쓰세요.."
                aria-label="Username" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-text">내용</span>
            <textarea class="form-control" v-model="attractionReviewSave.content" placeholder="내용을 쓰세요.."
                aria-label="With textarea"></textarea>
        </div>
        <div class="d-flex justify-content-end mt-3">
            <button type="submit" class="btn btn-custom mt-1">작성 완료</button>
        </div>
    </form>
</template>

<style scoped>
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