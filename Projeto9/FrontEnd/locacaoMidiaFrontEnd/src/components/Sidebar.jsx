import React from 'react';
import { Link } from 'react-router-dom';

function Sidebar() {
  const links = [
    { name: 'Tela Principal', path: '/' },
    { name: 'Estados', path: '/estados' },
    { name: 'Cidades', path: '/cidades' },
    { name: 'Clientes', path: '/clientes' },
    { name: 'Mídias', path: '/midias' },
    { name: 'Exemplares', path: '/exemplares' },
    { name: 'Locações', path: '/locacao' },
    { name: 'Ator/Atrizes', path: '/ator'},
    { name: 'Classificação Etária', path: '/classificacaoEtaria'},
    { name: 'Classificação Interna', path: '/classificacaoInterna'},
    { name: 'Gênero', path: '/genero'},  
    { name: 'Tipo', path: '/tipo'}
  ];

  return (
    <nav style={styles.sidebar}>
      <h3 style={styles.header}>Locadora Menu</h3>
      <ul style={styles.ul}>
        {links.map((link) => (
          <li key={link.name} style={styles.li}>
            <Link to={link.path} style={styles.link}>
              {link.name}
            </Link>
          </li>
        ))}
      </ul>
    </nav>
  );
}

const styles = {
  sidebar: {
    width: '200px',
    backgroundColor: '#333',
    color: 'white',
    padding: '15px 0',
    height: '100vh', 
  },
  header: {
    textAlign: 'center',
    marginBottom: '20px',
  },
  ul: {
    listStyleType: 'none',
    padding: 0,
  },
  li: {
    padding: '10px 15px',
    borderBottom: '1px solid #444',
  },
  link: {
    color: 'white',
    textDecoration: 'none',
    display: 'block',
  },
};

export default Sidebar;