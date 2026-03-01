import Sidebar from "./Sidebar";
import Header from "./Header";

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <div style={{ display: "flex", height: "100vh" }}>

      <Sidebar />

      <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
        <Header />

        <main style={{ padding: "20px", flex: 1, background: "#f4f6f9" }}>
          {children}
        </main>
      </div>

    </div>
  );
}