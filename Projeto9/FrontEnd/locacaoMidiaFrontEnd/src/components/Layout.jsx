import React from 'react';
import Sidebar from './Sidebar';

function Layout({ children }) {
  return (
    <div style={layoutStyles.container}>
      <Sidebar />
      <main style={layoutStyles.content}>
        {children} {/* Aqui é onde o conteúdo da sua rota será injetado */}
      </main>
    </div>
  );
}

const layoutStyles = {
  container: {
    display: 'flex', // Usa Flexbox para colocar itens lado a lado
  },
  content: {
    marginLeft: '200px', // Ocupa o espaço da largura da Sidebar
    padding: '20px',
    flexGrow: 1, // Permite que o conteúdo ocupe o restante da tela
  },
};

export default Layout;