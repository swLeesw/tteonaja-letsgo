import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listArticle(params, success, fail) {
  local
    .get(`/board/${params.boardType}/list`, { params: params })
    .then(success)
    .catch(fail);
}

function getArticle(params, success, fail) {
  local
    .get(`board/${params.boardType}/${params.articleNo}`)
    .then(success)
    .catch(fail);
}

export { listArticle, getArticle };
