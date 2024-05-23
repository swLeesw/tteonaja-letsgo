import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function listReview(param, success, fail) {
  await local.get(`review/list/${param}`, param).then(success).catch(fail);
}

//check: false => likeReview, check: true => deleteReview
async function likeReview(reviewNo, userId, success, fail) {
  await local.get(`/review/like/${reviewNo}/${userId}`, reviewNo, userId).then(success).catch(fail);
}

async function registReview(param, success, fail) {
  await local.post(`review/regist`, param).then(success).catch(fail);
}

async function deleteReview(param, success, fail) {
  await local.delete(`review/${param}`, param).then(success).catch(fail);
}

export { listReview, likeReview, registReview, deleteReview };
