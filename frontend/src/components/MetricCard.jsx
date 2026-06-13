export default function MetricCard({ title, value, subtitle, color }) {
  return (
    <div className="metric-card" style={{ borderTopColor: color }}>
      <div className="metric-value" style={{ color }}>
        {value?.toLocaleString() ?? "—"}
      </div>
      <div className="metric-title">{title}</div>
      {subtitle && <div className="metric-subtitle">{subtitle}</div>}
    </div>
  );
}
