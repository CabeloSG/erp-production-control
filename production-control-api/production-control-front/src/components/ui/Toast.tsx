import { useEffect } from "react";

type Props = {
  message: string;
  type: "success" | "error";
  onClose: () => void;
};

export default function Toast({ message, type, onClose }: Props) {

  useEffect(() => {
    const timer = setTimeout(onClose, 3000);
    return () => clearTimeout(timer);
  }, []);

  return (
    <div
      style={{
        position: "fixed",
        top: 20,
        right: 20,
        padding: "12px 20px",
        borderRadius: 8,
        color: "white",
        fontWeight: 600,
        background: type === "success" ? "#16a34a" : "#dc2626",
        boxShadow: "0 8px 20px rgba(0,0,0,0.2)",
        animation: "fadeIn 0.3s ease"
      }}
    >
      {message}
    </div>
  );
}