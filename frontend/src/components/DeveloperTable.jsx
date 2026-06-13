export default function DeveloperTable({ data }) {
  return (
    <div className="table-container">
      <h3 className="chart-title">Resumen por Desarrollador</h3>
      <table className="dev-table">
        <thead>
          <tr>
            <th>Desarrollador</th>
            <th>Equipo</th>
            <th>Commits</th>
            <th>Tareas</th>
            <th>Incidencias</th>
            <th>Lineas Anadidas</th>
            <th>Lineas Eliminadas</th>
          </tr>
        </thead>
        <tbody>
          {data.map((dev) => (
            <tr key={dev.developerId}>
              <td className="dev-name">{dev.name}</td>
              <td>
                <span className={`team-badge team-${dev.team.toLowerCase()}`}>
                  {dev.team}
                </span>
              </td>
              <td>{dev.totalCommits}</td>
              <td>{dev.totalTasks}</td>
              <td>{dev.totalIncidents}</td>
              <td className="lines-added">+{dev.totalLinesAdded?.toLocaleString()}</td>
              <td className="lines-removed">-{dev.totalLinesRemoved?.toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
