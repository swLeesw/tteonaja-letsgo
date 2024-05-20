<script setup>
import { ref, onMounted } from 'vue';
import { listComment } from '@/api/board';
import BoardCommentListItem from './item/BoardCommentListItem.vue';

const comments = ref([]);
const props = defineProps({
    commentParams: Object
});

const getCommentsList = () => {
    console.log(props.commentParams.boardType)
    listComment(
        props.commentParams,
        (response) => {
            console.log(response);
            comments.value = response.data;
        },
        (error) => {
            console.log(error);
        }
    );
};

onMounted(() => {
    getCommentsList();
})

</script>

<template>
    <div class="container" v-for="(comment, index) in comments" :key="index">
        <BoardCommentListItem :comment=comment />
    </div>
</template>

<style scoped></style>