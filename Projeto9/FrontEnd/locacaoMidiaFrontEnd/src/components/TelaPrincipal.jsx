import React, { useState } from "react";

export default function Home() {
    const filmes = [
        { id: 1, titulo: "Skinamarink", img: "/imgs/skinamarink.jpg" },
        { id: 2, titulo: "Tudo em Todo Lugar ao Mesmo Tempo", img: "/imgs/everything.webp" },
        { id: 3, titulo: "Modern Family", img: "/imgs/modern-family.jpg" },
        { id: 4, titulo: "A Voz do Silêncio", img: "/imgs/koe.jpg" },
        { id: 5, titulo: "WALL-E", img: "/imgs/wall-e.jpg" },
        { id: 6, titulo: "Blade Runner 2049", img: "/imgs/blade.jpg" },
        { id: 7, titulo: "Rio", img: "/imgs/rio.jpg" },
        { id: 8, titulo: "Trem-Bala", img: "/imgs/trem.jpg" },
    ];

    const [index, setIndex] = useState(0);

    function next() {
        setIndex((i) => (i + 1) % filmes.length);
    }

    function prev() {
        setIndex((i) => (i - 1 + filmes.length) % filmes.length);
    }

    return (
        <div className="home-container">
            <h1 className="welcome-title">🎬 Bem-vindo à Locadora 🎬</h1>

            <div className="carousel">
                <button className="nav-btn" onClick={prev}>❮</button>

                <div className="carousel-window">
                    <div
                        className="carousel-track"
                        style={{ transform: `translateX(-${index * 100}%)` }}
                    >
                        {filmes.map((filme) => (
                            <div className="item" key={filme.id}>
                                <img src={filme.img} alt={filme.titulo} />
                                <h3>{filme.titulo}</h3>
                            </div>
                        ))}
                    </div>
                </div>

                <button className="nav-btn" onClick={next}>❯</button>
            </div>
        </div>
    );
}
