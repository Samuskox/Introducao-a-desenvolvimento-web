import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import EstadoList from './components/EstadosList.jsx';
import CidadesList from './components/CidadesList.jsx';
import ClientesList from './components/ClientesList.jsx';
import EstadoForm from './components/EstadosForm.jsx';
import CidadesForm from './components/CidadesForm.jsx';
import Layout from './components/Layout';
import ClientesForm from './components/ClientesForm.jsx';
import MidiaList from './components/MidiaList.jsx';
import MidiaForm from './components/MidiaForm.jsx';
import ExemplaresList from './components/ExemplaresList.jsx';
import ExemplaresForm from './components/ExemplaresForm.jsx';
import LocacaoForm from './components/LocacaoForm.jsx';
import LocacaoList from './components/LocacaoList.jsx';
import AtorList from './components/AtorList.jsx';
import AtorForm from './components/AtorForm.jsx';
import ClassificacaoEtariaList from './components/ClassificacaoEtariaList.jsx';
import ClassificacaoEtariaForm from './components/ClassificacaoEtariaForm.jsx';
import ClassificacaoInternaList from './components/ClassificacaoInternaList.jsx';
import ClassificacaoInternaForm from './components/ClassificacaoInternaForm.jsx';
import GeneroList from './components/GeneroList.jsx';
import GeneroForm from './components/GeneroForm.jsx';
import TipoList from './components/TipoList.jsx';
import TipoForm from './components/TipoForm.jsx';

function App() {

  return (
    <Router>
      <Layout>
        <Routes>
          {/* Rota padrão */}
          <Route path="/" element={<h1>Bem-vindo à Locadora!</h1>} />
          {/* Adicione outras rotas para clientes, locacoes, etc., aqui */}

          <Route path="/estados" element={<EstadoList/>} />
          <Route path="/estados/novo" element={<EstadoForm/>} />

          <Route path="/cidades" element={<CidadesList/>} />
          <Route path="/cidades/novo" element={<CidadesForm/>} />

          <Route path="/clientes" element={<ClientesList/>} />
          <Route path="/clientes/novo" element={<ClientesForm/>} />

          <Route path="/midias" element={<MidiaList/>} />
          <Route path="/midias/novo" element={<MidiaForm/>} />

          <Route path="/exemplares" element={<ExemplaresList/>}/>
          <Route path="/exemplares/novo" element={<ExemplaresForm/>}/>

          <Route path="/locacao" element={<LocacaoList/>}/>
          <Route path="/locacao/novo" element={<LocacaoForm/>}/>

          <Route path="/ator" element={<AtorList/>}/>
          <Route path="/ator/novo" element={<AtorForm/>}/>

          <Route path="/classificacaoEtaria" element={<ClassificacaoEtariaList/>}/>
          <Route path="/classificacaoEtaria/novo" element={<ClassificacaoEtariaForm/>}/>

          <Route path="/classificacaoInterna" element={<ClassificacaoInternaList/>}/>
          <Route path="/classificacaoInterna/novo" element={<ClassificacaoInternaForm/>}/>

          <Route path="/genero" element={<GeneroList/>}/>
          <Route path="/genero/novo" element={<GeneroForm/>}/>

          <Route path="/tipo" element={<TipoList/>}/>
          <Route path="/tipo/novo" element={<TipoForm/>}/>
        </Routes>
      </Layout>
    </Router>
  )
}

export default App
