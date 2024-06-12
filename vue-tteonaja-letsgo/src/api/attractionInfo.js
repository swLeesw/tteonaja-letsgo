import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function getAttraction(params, success, fail) {
  await local.get(`/attractionInfo/region`, { params: params }).then(success).catch(fail);
}

async function getAdditionalAttraction(params, success, fail) {
  await local.get(`/attractionInfo/region/additional`, {params: params}).then(success).catch(fail);
}

async function getSido(success, fail) {
  await local.get(`/attractionInfo/sido`).then(success).catch(fail);
}

async function getGugun(param, success, fail) {
  await local.get(`/attractionInfo/gugun/${param}`).then(success).catch(fail);
}

async function getTopAttraction(success, fail) {
  await local.get(`/attractionInfo/top`).then(success).catch(fail);
}

async function likeAttraction(id, userId, success, fail) {
  await local.get(`/attractionInfo/like/${id}/${userId}`, id, userId).then(success).catch(fail);
}

async function getAttractionById(attractionId, success, fail) {
  await local.get(`attractionInfo/attraction/${attractionId}`).then(success).catch(fail)
}
export { getAttraction, getAdditionalAttraction, getSido, getGugun, getTopAttraction, getAttractionById, likeAttraction};
