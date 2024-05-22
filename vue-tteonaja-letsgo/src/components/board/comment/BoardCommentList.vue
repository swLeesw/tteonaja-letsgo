<script setup>
import { ref, onMounted, watch } from 'vue';
import { listComment } from '@/api/board';
import BoardCommentListItem from './item/BoardCommentListItem.vue';

const comments = ref([]);
const props = defineProps({
    commentParams: Object,
    writeChecker: String,
    boardType: String
});
const emits = defineEmits(['deletedComment', 'completeWrite']);
const writeChecker = ref(props.writeChecker)
watch(writeChecker, (newValue, oldValue) => {
    getCommentsList();
    emits('completeWrite');
})
const getCommentsList = () => {
    console.log(props.commentParams.boardType);
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

const refreshCommentsList = async () => {
    console.log(props.commentParams.boardType);
    await listComment(
        props.commentParams,
        (response) => {
            console.log(response);
            comments.value = response.data;
        },
        (error) => {
            console.log(error);
        }
    );

    emits('deletedComment');
};

onMounted(() => {
    getCommentsList();
})

</script>

<template>
    <div class="container" v-for="(comment, index) in comments" :key="index">
        <BoardCommentListItem :board-type="props.boardType" :comment=comment @deleted-comment="refreshCommentsList" />
    </div>
</template>

<style scoped></style>