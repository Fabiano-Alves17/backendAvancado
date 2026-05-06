import { Component } from '@angular/core';
import { AuthorListComponent } from './components/author-list/author-list';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [AuthorListComponent],
  template: `
    <main>
      <nav class="glass-nav">
        <div class="logo">FRANKENSTEIN <span>MONOLITH</span></div>
        <div class="status-badge">API: DISCONNECTED</div>
      </nav>
      
      <app-author-list></app-author-list>
      
      <footer class="footer">
        <p>LABORATÓRIO DE BACKEND AVANÇADO - SPRING BOOT</p>
      </footer>
    </main>
  `,
  styles: [`
    .glass-nav {
      background: rgba(255, 255, 255, 0.05);
      backdrop-filter: blur(10px);
      padding: 1rem 5%;
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 1px solid rgba(255, 255, 255, 0.1);
    }
    .logo {
      font-weight: 800;
      letter-spacing: 2px;
    }
    .logo span { color: #818cf8; }
    .status-badge {
      background: rgba(239, 68, 68, 0.2);
      color: #ef4444;
      padding: 4px 12px;
      border-radius: 20px;
      font-size: 0.8rem;
      border: 1px solid rgba(239, 68, 68, 0.3);
    }
    .footer {
      position: fixed;
      bottom: 0;
      width: 100%;
      text-align: center;
      padding: 1rem;
      font-size: 0.7rem;
      color: #475569;
      letter-spacing: 3px;
    }
  `]
})
export class AppComponent {
  title = 'frontend';
}
