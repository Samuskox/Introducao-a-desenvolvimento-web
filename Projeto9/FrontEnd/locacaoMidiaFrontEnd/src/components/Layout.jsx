import Starfield from "./Starfield";
import Sidebar from './Sidebar';

function Layout({ children }) {

  return (
    <div style={layoutStyles.container}>
      <div className="noise-overlay"></div>
      <Starfield amount={120} />  {/* quantidade de estrelas */}
      <Sidebar />
      <main style={layoutStyles.content}>
        {children} {/* Aqui é onde o conteúdo da sua rota será injetado */}
      </main>
    </div>
  );
}

const layoutStyles = {
  container: {
    display: "flex",
    height: "100vh",
    width: "100vw",
  },
  content: {
    flex: 1,
    display: "flex",
    justifyContent: "center", // centro horizontal
    alignItems: "flex-start",     // centro vertical
    padding: "20px",
    overflow: "auto", // evita bug de corte
  },
};

export default Layout;