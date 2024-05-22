import { localAxios } from "@/util/http-commons";

const local = localAxios();

function getAttraction(params, success, fail) {
  local.get(`/attractionInfo/region`, { params: params }).then(success).catch(fail);
}

function getSido(success, fail) {
  local.get(`/attractionInfo/sido`).then(success).catch(fail);
}

function getGugun(param, success, fail) {
  local.get(`/attractionInfo/gugun/${param}`).then(success).catch(fail);
}

function getTop(success, fail) {
  local.get(`/attractionInfo/top`).then(success).catch(fail);
}

export { getAttraction, getSido, getGugun, getTop };
