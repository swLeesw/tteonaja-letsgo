<script setup>
import { deleteComment } from '@/api/board';
import router from '@/router';
import { jwtDecode } from 'jwt-decode';
import Swal from 'sweetalert2';
import { onMounted, ref } from 'vue';

const props = defineProps({
    comment: Object,
    boardType: String
});

const currentUserId = ref("")

const emits = defineEmits(['deletedComment'])

const removeComment = () => {
    Swal.fire({
        title: "댓글을 삭제하시겠습니까?",
        text: "삭제 후 복구할 수 없음에 주의하세요!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "확인",
        cancelButtonText: "취소"
    }).then((result) => {
        if (result.isConfirmed) {
            deleteComment(
                {
                    boardType: props.boardType,
                    commentNo: props.comment.commentNo
                },
                (response) => {
                    Swal.fire({
                        title: "삭제되었습니다.",
                        icon: "success",
                        confirmButtonText: "확인",
                    }).then((result) => {
                        emits('deletedComment', props.comment.commentNo)
                        // router.go(0);
                    });
                },
                (error) => {
                    console.log(error);
                }
            );
        }
    });
}

onMounted(() => {
    currentUserId.value = jwtDecode(sessionStorage.getItem(['accessToken'])).userId;
})
</script>

<template>
    <div class="p-2">
        <div class="d-flex justify-content-between ">
            <span class="d-flex align-items-center">{{ props.comment.userId }} <button
                    v-if="currentUserId === props.comment.userId"
                    class="btn bg-light rounded btn-sm pt-0 pb-0 ps-1 pe-1 ms-3"
                    @click="removeComment">삭제</button></span>
            <span>{{ props.comment.registerTime }}</span>
        </div>
        <p class="mt-3" v-html="props.comment.content"></p>
        <hr class="text-body-tertiary">
    </div>
</template>

<style scoped></style>