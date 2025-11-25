import { useEffect } from "react";

export default function Starfield({ amount = 80 }) {
    useEffect(() => {
        const container = document.getElementById("stars-container");

        const getCurrentPosition = (star) => {
            const style = window.getComputedStyle(star);
            const matrix = new DOMMatrixReadOnly(style.transform);
            return { x: matrix.m41, y: matrix.m42 };
        };

        function animateStar(star) {
            const { x, y } = getCurrentPosition(star);

            const angle = Math.random() * Math.PI * 2;
            const distance = Math.random() * 40 + 20;
            const duration = Math.random() * 3000 + 1500; // 15–45s

            const newX = x + Math.cos(angle) * distance;
            const newY = y + Math.sin(angle) * distance;

            const animation = star.animate(
                [
                    { transform: `translate(${x}px, ${y}px)` },
                    { transform: `translate(${newX}px, ${newY}px)` }
                ],
                {
                    duration,
                    easing: "ease-in-out",
                    fill: "forwards"
                }
            );

            animation.onfinish = () => {
                star.style.transform = `translate(${newX}px, ${newY}px)`;
                animateStar(star); // próxima direção
            };
        }

        function spawnStar() {
            const star = document.createElement("div");
            star.classList.add("star");

            const size = Math.random() * 2 + 1;
            star.style.width = `${size}px`;
            star.style.height = `${size}px`;

            star.style.left = Math.random() * 100 + "vw";
            star.style.top = Math.random() * 100 + "vh";
            star.style.opacity = "1";

            star.style.transform = "translate(0, 0)";
            container.appendChild(star);

            animateStar(star);

            // Vida da estrela (20–50s)
            const lifespan = Math.random() * 3000 + 200;

            setTimeout(() => {
                // fade out suave
                star.animate(
                    [{ opacity: 1 }, { opacity: 0 }],
                    { duration: 1500, fill: "forwards" }
                ).onfinish = () => {
                    star.remove();
                    spawnStar(); // renasce uma nova estrela
                };
            }, lifespan);
        }

        for (let i = 0; i < amount; i++) {
            spawnStar();
        }
    }, [amount]);

    return <div id="stars-container" className="stars-container"></div>;
}
