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
    .get(`/board/${params.boardType}/${params.articleNo}`)
    .then(success)
    .catch(fail);
}

function registArticle(article, success, fail) {
  local
    .post(`/board/free/regist`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

function modifyArticle(params, success, fail) {
  local
    .put(`/board/${params.boardType}`, JSON.stringify(params.article))
    .then(success)
    .catch(fail);
}

function deleteArticle(params, success, fail) {
  local
    .delete(`/board/${params.boardType}/${params.articleNo}`)
    .then(success)
    .catch(fail);
}

export {
  listArticle,
  getArticle,
  registArticle,
  modifyArticle,
  deleteArticle
};
