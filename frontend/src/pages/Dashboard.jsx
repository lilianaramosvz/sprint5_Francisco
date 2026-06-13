import { useState, useEffect } from 'react'
import { fetchOverview, fetchSummary } from '../api/api'
import MetricCard from '../components/MetricCard'
import CommitsChart from '../components/CommitsChart'
import TasksChart from '../components/TasksChart'
import LinesChart from '../components/LinesChart'
import DeveloperTable from '../components/DeveloperTable'
import './Dashboard.css'

export default function Dashboard() {
  const [overview, setOverview] = useState(null)
  const [summary, setSummary]   = useState([])
  const [loading, setLoading]   = useState(true)
  const [error, setError]       = useState(null)

  useEffect(() => {
    Promise.all([fetchOverview(), fetchSummary()])
      .then(([ov, sum]) => {
        setOverview(ov)
        setSummary(sum)
        setLoading(false)
      })
      .catch(() => {
        setError('No se pudo conectar con el backend. Asegurate de que Spring Boot esta corriendo en http://localhost:8080')
        setLoading(false)
      })
  }, [])

  if (loading) return <div className="loading">Cargando datos del dashboard...</div>
  if (error)   return <div className="error">{error}</div>

  return (
    <div className="dashboard">
      <h1 className="dashboard-title">Dashboard de Productividad</h1>
      <p className="dashboard-subtitle">Metricas de rendimiento del equipo de desarrollo</p>

      <div className="metrics-grid">
        <MetricCard title="Total Commits"        value={overview?.totalCommits}    subtitle="Todos los desarrolladores" color="#4f46e5" />
        <MetricCard title="Tareas Completadas"   value={overview?.totalTasks}      subtitle="Todos los desarrolladores" color="#10b981" />
        <MetricCard title="Incidencias Resueltas" value={overview?.totalIncidents} subtitle="Todos los desarrolladores" color="#f59e0b" />
        <MetricCard title="Lineas de Codigo"     value={overview?.totalLinesAdded} subtitle="Lineas anadidas"           color="#ef4444" />
        <MetricCard title="Desarrolladores"      value={overview?.totalDevelopers} subtitle="En el sistema"             color="#8b5cf6" />
      </div>

      <div className="charts-grid">
        <CommitsChart data={summary} />
        <TasksChart   data={summary} />
      </div>

      <div className="charts-grid">
        <LinesChart data={summary} />
      </div>

      <DeveloperTable data={summary} />
    </div>
  )
}
