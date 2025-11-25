import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css';

function Sidebar() {
  const links = [
    { name: 'Tela Principal', path: '/' },
    { name: 'Estados', path: '/estados' },
    { name: 'Cidades', path: '/cidades' },
    { name: 'Clientes', path: '/clientes' },
    { name: 'Mídias', path: '/midias' },
    { name: 'Exemplares', path: '/exemplares' },
    { name: 'Locações', path: '/locacao' },
    { name: 'Ator/Atrizes', path: '/ator' },
    { name: 'Classificação Etária', path: '/classificacaoEtaria' },
    { name: 'Classificação Interna', path: '/classificacaoInterna' },
    { name: 'Gênero', path: '/genero' },
    { name: 'Tipo', path: '/tipo' }
  ];

  return (
    <nav className="sidebar">
      <h3 className="sidebar-title">Menu</h3>

      <ul className="sidebar-list">
        {links.map((link) => (
          <li key={link.name} className="sidebar-item">
            <Link to={link.path} className="sidebar-link">
              {link.name}
            </Link>
          </li>
        ))}
      </ul>
    </nav>
  );
}

export default Sidebar;
