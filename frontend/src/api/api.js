import axios from "axios";

const BASE = "/api";

export const fetchOverview = () =>
  axios.get(`${BASE}/metrics/overview`).then((r) => r.data);
export const fetchSummary = () =>
  axios.get(`${BASE}/metrics/summary`).then((r) => r.data);
export const fetchDevelopers = () =>
  axios.get(`${BASE}/developers`).then((r) => r.data);
export const fetchMetricsByDeveloper = (id) =>
  axios.get(`${BASE}/metrics/developer/${id}`).then((r) => r.data);
