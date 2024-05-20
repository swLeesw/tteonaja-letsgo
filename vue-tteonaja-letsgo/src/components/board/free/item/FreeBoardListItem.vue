<script setup>
import { listComment } from '@/api/board';
import { onMounted, ref } from 'vue';
import { RouterLink } from 'vue-router';
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const props = defineProps({ elem: Object });

const commentNum = ref(0)

const getCommentsList = () => {
    // console.log(elem.commentParams.boardType);
    listComment(
        {
            articleNo: props.elem.article.articleNo,
            boardType: props.elem.boardType
        },
        (response) => {
            commentNum.value = response.data.length;
        },
        (error) => {
            console.log(error);
        }
    );
};

onMounted(() => {
    getCommentsList()
})

</script>

<template>
    <tr>
        <td scope="row">{{ elem.total - (VITE_ARTICLE_LIST_SIZE * (elem.currentPage - 1) + elem.index) }}</td>
        <td>
            <RouterLink class="del-deco fw-bold"
                :to="{ name: 'free-detail', params: { id: props.elem.article.articleNo } }">{{
                elem.article.subject }} <span v-if="commentNum !== 0">({{ commentNum }})</span></RouterLink>
        </td>
        <td>{{ elem.article.userId }}</td>
        <td>{{ elem.article.registerTime }}</td>
        <td>{{ elem.article.hit }}</td>
    </tr>
</template>

<style scoped>
.del-deco {
    text-decoration: none;
    color: black;
}
</style>